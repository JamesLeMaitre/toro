package com.torocommunication.torofull.service.security;

import com.torocommunication.torofull.entities.security.AppRole;
import com.torocommunication.torofull.entities.security.AppUsers;
import com.torocommunication.torofull.repo.security.AppRoleRepository;
import com.torocommunication.torofull.repo.security.AppUsersRepository;
import com.torocommunication.torofull.security.exceptions.PasswordNotMatchException;
import com.torocommunication.torofull.security.exceptions.RoleNotFoundException;
import com.torocommunication.torofull.security.request.LoginRequest;
import com.torocommunication.torofull.security.request.RegisterRequest;
import com.torocommunication.torofull.security.request.ResetPasswordRequest;
import com.torocommunication.torofull.security.response.AppUserResponse;
import com.torocommunication.torofull.security.utils.JwtUtils;
import com.torocommunication.torofull.security.utils.UserPrincipal;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
@AllArgsConstructor
public class UserServiceImplementation implements AppUsersService {

    private final AppUsersRepository userRepository;
    private final JwtUtils jwtUtils;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final AppRoleRepository roleRepository;
    private final EmailService emailService;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<AppUsers> user = userRepository.findByUsername(username);
        user.orElseThrow(() -> new UsernameNotFoundException("Utilisateur n'existe pas"));
        return user.map(UserPrincipal::new).get();
    }

    @Override
    public String authenticate(LoginRequest request, AuthenticationManager authenticationManager) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword())
        );
        Optional<AppUsers> user = userRepository.findByUsername(request.getUsername());
        user.orElseThrow(() -> new UsernameNotFoundException("Utilisateur Non trouvée!"));
        return jwtUtils.generateJwtToken(user.map(UserPrincipal::new).get());
    }

    @Override
    public AppUserResponse authUser(Authentication authentication) {
        AppUserResponse response = new AppUserResponse();
        String username = authentication.getName();
        Optional<AppUsers> user = userRepository.findByUsername(username);
        user.orElseThrow(() -> new UsernameNotFoundException("User Not found"));
        BeanUtils.copyProperties(user.get(), response);
        return response;
    }

    @Override
    public AppUserResponse storeUser(RegisterRequest registerRequest) throws RoleNotFoundException, MessagingException {
        AppUserResponse response = new AppUserResponse();
        registerRequest.setPassword(bCryptPasswordEncoder.encode(registerRequest.getPassword()));
        AppUsers user = new AppUsers();
        BeanUtils.copyProperties(registerRequest, user);

        AppUsers newUser = userRepository.save(user);
        addRoleToUser(registerRequest.getRoleName(), newUser.getUsername());
        emailService.sendNewEmail(newUser.getUsername(), newUser.getEmail(), "Activez votre compte");

        BeanUtils.copyProperties(newUser, response);
        return response;
    }

    @Override
    public AppUserResponse updateUser(RegisterRequest request, Authentication authentication) {
        AppUserResponse response = new AppUserResponse();

        Optional<AppUsers> user = userRepository.findByUsername(authentication.getName());
        user.orElseThrow(() -> new UsernameNotFoundException("Utilisateur n'existe pas"));
        BeanUtils.copyProperties(request, user.get());

        AppUsers updateUser = userRepository.save(user.get());
        BeanUtils.copyProperties(updateUser, response);

        return response;
    }

    @Override
    public void removeUser(String username) {
        Optional<AppUsers> user = userRepository.findByUsername(username);
        user.orElseThrow(() -> new UsernameNotFoundException("Utilisateur n'existe pas"));
        userRepository.delete(user.get());
    }

    @Override
    public AppUserResponse resetPassword(ResetPasswordRequest request, String username) throws PasswordNotMatchException {
        AppUserResponse response = new AppUserResponse();

        Optional<AppUsers> user = userRepository.findByUsername(username);
        user.orElseThrow(() -> new UsernameNotFoundException("Utilisateur n'existe pas"));

        if (!bCryptPasswordEncoder.matches(request.getOld_password(), user.get().getPassword())){
            throw new PasswordNotMatchException("Votre ancien mot de passe saisi es incorrect");
        }
        if (!request.getNew_password().equals(request.getConfirm_password())){
            throw new PasswordNotMatchException("Veuillez saisir les même mot de passe");
        }

        user.get().setPassword(bCryptPasswordEncoder.encode(request.getNew_password()));
        AppUsers userPassword = userRepository.save(user.get());
        BeanUtils.copyProperties(userPassword, response);
        return response;
    }

    @Override
    public AppUserResponse disabledAccount(String username) {
        AppUserResponse response = new AppUserResponse();
        Optional<AppUsers> user = userRepository.findByUsername(username);
        user.orElseThrow(() -> new UsernameNotFoundException("Utilisateur n'existe pas"));
        user.get().setIsActive(false);
        AppUsers userPassword = userRepository.save(user.get());
        BeanUtils.copyProperties(userPassword, response);
        return response;
    }

    @Override
    public AppUserResponse enabledAccount(String username) {
        AppUserResponse response = new AppUserResponse();
        Optional<AppUsers> user = userRepository.findByUsername(username);
        user.orElseThrow(() -> new UsernameNotFoundException("Utilisateur n'existe pas"));

        user.get().setIsActive(true);
        AppUsers userPassword = userRepository.save(user.get());
        BeanUtils.copyProperties(userPassword, response);
        return response;
    }

    @Override
    public AppRole saveRole(AppRole role) {
        return roleRepository.save(role);
    }

    public void addRoleToUser(String roleName, String username) throws RoleNotFoundException, UsernameNotFoundException {
        Optional<AppRole> role = roleRepository.findByRoleName(roleName);
        role.orElseThrow(() -> new RoleNotFoundException("Role n'existe pas"));
        Optional<AppUsers> user = userRepository.findByUsername(username);
        user.orElseThrow(() -> new UsernameNotFoundException("Utilisateur n'existe pas"));
        user.get().getRoles().add(role.get());
    }

    @Override
    public List<AppUsers> getAll(){
       return userRepository.findAll();
    }

}
