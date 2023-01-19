package com.torocommunication.torofull.service;

import com.torocommunication.torofull.entities.DetailSA;
import com.torocommunication.torofull.entities.RoleUEA;
import com.torocommunication.torofull.entities.TypeUEA;
import com.torocommunication.torofull.entities.UtilisateurUEA;
import com.torocommunication.torofull.repo.RoleUEARepo;
import com.torocommunication.torofull.repo.UtilisateurUEARepo;
import com.torocommunication.torofull.security.exceptions.PasswordNotMatchException;
import com.torocommunication.torofull.security.exceptions.RoleNotFoundException;
import com.torocommunication.torofull.security.request.RegisterRequest;
import com.torocommunication.torofull.security.request.ResetPasswordRequest;
import com.torocommunication.torofull.security.response.AppUserReponseDem;
import com.torocommunication.torofull.security.response.AppUserResponseStagiaire;
import com.torocommunication.torofull.security.utils.JwtUtils;
import com.torocommunication.torofull.security.utils.UserPrincipal;
import com.torocommunication.torofull.service.serviceInterface.DetailSAInterface;
import com.torocommunication.torofull.service.serviceInterface.EmailService;
import com.torocommunication.torofull.service.serviceInterface.TypeUEAInterface;
import com.torocommunication.torofull.service.serviceInterface.UtilisateurUEAInterface;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import  com.torocommunication.torofull.security.request.LoginRequest;

import javax.mail.MessagingException;
import javax.transaction.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
@AllArgsConstructor
public class UtilisateurUEAServiceImpl implements UtilisateurUEAInterface, UserDetailsService {
    private final UtilisateurUEARepo userRepo;
    private final RoleUEARepo roleRepo;
    private  final PasswordEncoder passwordEncoder;

    private  final DetailSAInterface detailSAInterface;


    private  final TypeUEAInterface typeUEAInterface;





    private final JwtUtils jwtUtils;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    private final EmailService emailService;

  



    @Override
    public UtilisateurUEA saveUser(UtilisateurUEA user) {

        System.out.println("enregistrement de l'utilisateur {} dans la base de données"+user.getNom());
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepo.save(user);
    }

    @Override
    public UtilisateurUEA getByID(Long id) {
        return userRepo.findById(id).orElse(null);
    }





    @Override
    public UtilisateurUEA getByUsername(String username) {
        System.out.println("recherche de  l'utilisateur {} "+username);
        return userRepo.findByUsername(username).get();
    }

    @Override
    public List<UtilisateurUEA> getUsers() {
        System.out.println("recherche de tout les utilisateurs ");
        return userRepo.findAll();
    }












    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<UtilisateurUEA> user = userRepo.findByUsername(username);
        user.orElseThrow(() -> new UsernameNotFoundException("Utilisateur n'existe pas"));
        return user.map(UserPrincipal::new).get();
    }

    @Override
    public String authenticate(LoginRequest request, AuthenticationManager authenticationManager) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword())
        );
        Optional<UtilisateurUEA> user = userRepo.findByUsername(request.getUsername());
        user.orElseThrow(() -> new UsernameNotFoundException("Utilisateur Non trouvée!"));
        return jwtUtils.generateJwtToken(user.map(UserPrincipal::new).get());
    }

    @Override
    public AppUserResponseStagiaire authUser(Authentication authentication) {
        AppUserResponseStagiaire response = new AppUserResponseStagiaire();
        String username = authentication.getName();
        Optional<UtilisateurUEA> user = userRepo.findByUsername(username);
        user.orElseThrow(() -> new UsernameNotFoundException("Utilisateur Non trouvée!"));
        BeanUtils.copyProperties(user.get(), response);
        return response;
    }

    @Override
    public AppUserResponseStagiaire storeUser(RegisterRequest registerRequest) throws RoleNotFoundException, MessagingException {
        AppUserResponseStagiaire response = new AppUserResponseStagiaire();
        registerRequest.setPassword(bCryptPasswordEncoder.encode(registerRequest.getPassword()));
        UtilisateurUEA user = new UtilisateurUEA();

        BeanUtils.copyProperties(registerRequest, user);
        DetailSA detailSA=  detailSAInterface.getByIds(registerRequest.getDetailSA().longValue());

        TypeUEA typeUEA=typeUEAInterface.getByIds(registerRequest.getTypeUEA().longValue());


        System.out.println("detail " +detailSA);

        System.out.println("type" +registerRequest.getTypeUEA() );

        user.setDetailSA(detailSA);
        user.setTypeUEA(typeUEA);

        UtilisateurUEA newUser = userRepo.save(user);
        addRoleToUser(registerRequest.getRoleName(), newUser.getUsername());
        emailService.sendNewEmail(newUser.getUsername(), newUser.getEmail(), "Activez votre compte");

        BeanUtils.copyProperties(newUser, response);
        return response;
    }

    @Override
    public AppUserReponseDem storeUserDem(RegisterRequest registerRequest) throws RoleNotFoundException, MessagingException {
        return null;
    }

    @Override
    public AppUserResponseStagiaire updateUser(RegisterRequest request, Authentication authentication) {
        AppUserResponseStagiaire response = new AppUserResponseStagiaire();

        Optional<UtilisateurUEA> user = userRepo.findByUsername(authentication.getName());
        user.orElseThrow(() -> new UsernameNotFoundException("Utilisateur n'existe pas"));
        BeanUtils.copyProperties(request, user.get());

        UtilisateurUEA updateUser = userRepo.save(user.get());
        BeanUtils.copyProperties(updateUser, response);

        return response;
    }

    @Override
    public void removeUser(String username) {
        Optional<UtilisateurUEA> user = userRepo.findByUsername(username);
        user.orElseThrow(() -> new UsernameNotFoundException("Utilisateur n'existe pas"));
        userRepo.delete(user.get());
    }

    @Override
    public AppUserResponseStagiaire resetPassword(ResetPasswordRequest request, String username) throws PasswordNotMatchException {
        AppUserResponseStagiaire response = new AppUserResponseStagiaire();

        Optional<UtilisateurUEA> user = userRepo.findByUsername(username);
        user.orElseThrow(() -> new UsernameNotFoundException("Utilisateur n'existe pas"));

        if (!bCryptPasswordEncoder.matches(request.getOld_password(), user.get().getPassword())){
            throw new PasswordNotMatchException("Votre ancien mot de passe saisi es incorrect");
        }
        if (!request.getNew_password().equals(request.getConfirm_password())){
            throw new PasswordNotMatchException("Veuillez saisir les même mot de passe");
        }

        user.get().setPassword(bCryptPasswordEncoder.encode(request.getNew_password()));
        UtilisateurUEA userPassword = userRepo.save(user.get());
        BeanUtils.copyProperties(userPassword, response);
        return response;
    }

    @Override
    public AppUserResponseStagiaire disabledAccount(String username) {
        AppUserResponseStagiaire response = new AppUserResponseStagiaire();
        Optional<UtilisateurUEA> user = userRepo.findByUsername(username);
        user.orElseThrow(() -> new UsernameNotFoundException("Utilisateur n'existe pas"));

        user.get().setIsActive(false);
        UtilisateurUEA userPassword = userRepo.save(user.get());
        BeanUtils.copyProperties(userPassword, response);
        return response;
    }

    @Override
    public AppUserResponseStagiaire enabledAccount(String username) {
        AppUserResponseStagiaire response = new AppUserResponseStagiaire();
        Optional<UtilisateurUEA> user = userRepo.findByUsername(username);
        user.orElseThrow(() -> new UsernameNotFoundException("Utilisateur n'existe pas"));

        user.get().setIsActive(true);
        UtilisateurUEA userPassword = userRepo.save(user.get());
        BeanUtils.copyProperties(userPassword, response);
        return response;
    }

    @Override
    public UtilisateurUEA findByUsernameAndPassword(String username, String password) {

        UtilisateurUEA uea=userRepo.findByUsernameAndPassword(username,bCryptPasswordEncoder.encode(password));
        return uea;

    }

    @Override
    public int count() {
            return userRepo.countBy();

    }

    @Override
    public int countAdmin() {
        return userRepo.countByAdmin();
    }

    @Override
    public UtilisateurUEA getById(Long id) {
        return userRepo.findById(id).orElse(null);
    }

    @Override
    public RoleUEA saveRole(RoleUEA role) {
        return roleRepo.save(role);
    }

    public void addRoleToUser(String roleName, String username) throws RoleNotFoundException, UsernameNotFoundException {
        Optional<RoleUEA> role = roleRepo.findByName(roleName);
        role.orElseThrow(() -> new RoleNotFoundException("Role n'existe pas"));
        Optional<UtilisateurUEA> user = userRepo.findByUsername(username);
        user.orElseThrow(() -> new UsernameNotFoundException("Utilisateur n'existe pas"));
        user.get().getRoles().add(role.get());
    }

    private String buildEmail(String name, String link) {
        return "<div style=\"font-family:Helvetica,Arial,sans-serif;font-size:16px;margin:0;color:#0b0c0c\">\n" +
                "\n" +
                "<span style=\"display:none;font-size:1px;color:#fff;max-height:0\"></span>\n" +
                "\n" +
                "  <table role=\"presentation\" width=\"100%\" style=\"border-collapse:collapse;min-width:100%;width:100%!important\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\">\n" +
                "    <tbody><tr>\n" +
                "      <td width=\"100%\" height=\"53\" bgcolor=\"#0b0c0c\">\n" +
                "        \n" +
                "        <table role=\"presentation\" width=\"100%\" style=\"border-collapse:collapse;max-width:580px\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\" align=\"center\">\n" +
                "          <tbody><tr>\n" +
                "            <td width=\"70\" bgcolor=\"#0b0c0c\" valign=\"middle\">\n" +
                "                <table role=\"presentation\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\" style=\"border-collapse:collapse\">\n" +
                "                  <tbody><tr>\n" +
                "                    <td style=\"padding-left:10px\">\n" +
                "                  \n" +
                "                    </td>\n" +
                "                    <td style=\"font-size:28px;line-height:1.315789474;Margin-top:4px;padding-left:10px\">\n" +
                "                      <span style=\"font-family:Helvetica,Arial,sans-serif;font-weight:700;color:#ffffff;text-decoration:none;vertical-align:top;display:inline-block\">Confirm your email</span>\n" +
                "                    </td>\n" +
                "                  </tr>\n" +
                "                </tbody></table>\n" +
                "              </a>\n" +
                "            </td>\n" +
                "          </tr>\n" +
                "        </tbody></table>\n" +
                "        \n" +
                "      </td>\n" +
                "    </tr>\n" +
                "  </tbody></table>\n" +
                "  <table role=\"presentation\" class=\"m_-6186904992287805515content\" align=\"center\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\" style=\"border-collapse:collapse;max-width:580px;width:100%!important\" width=\"100%\">\n" +
                "    <tbody><tr>\n" +
                "      <td width=\"10\" height=\"10\" valign=\"middle\"></td>\n" +
                "      <td>\n" +
                "        \n" +
                "                <table role=\"presentation\" width=\"100%\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\" style=\"border-collapse:collapse\">\n" +
                "                  <tbody><tr>\n" +
                "                    <td bgcolor=\"#1D70B8\" width=\"100%\" height=\"10\"></td>\n" +
                "                  </tr>\n" +
                "                </tbody></table>\n" +
                "        \n" +
                "      </td>\n" +
                "      <td width=\"10\" valign=\"middle\" height=\"10\"></td>\n" +
                "    </tr>\n" +
                "  </tbody></table>\n" +
                "\n" +
                "\n" +
                "\n" +
                "  <table role=\"presentation\" class=\"m_-6186904992287805515content\" align=\"center\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\" style=\"border-collapse:collapse;max-width:580px;width:100%!important\" width=\"100%\">\n" +
                "    <tbody><tr>\n" +
                "      <td height=\"30\"><br></td>\n" +
                "    </tr>\n" +
                "    <tr>\n" +
                "      <td width=\"10\" valign=\"middle\"><br></td>\n" +
                "      <td style=\"font-family:Helvetica,Arial,sans-serif;font-size:19px;line-height:1.315789474;max-width:560px\">\n" +
                "        \n" +
                "            <p style=\"Margin:0 0 20px 0;font-size:19px;line-height:25px;color:#0b0c0c\">Hi " + name + ",</p><p style=\"Margin:0 0 20px 0;font-size:19px;line-height:25px;color:#0b0c0c\"> Thank you for registering. Please click on the below link to activate your account: </p><blockquote style=\"Margin:0 0 20px 0;border-left:10px solid #b1b4b6;padding:15px 0 0.1px 15px;font-size:19px;line-height:25px\"><p style=\"Margin:0 0 20px 0;font-size:19px;line-height:25px;color:#0b0c0c\"> <a href=\"" + link + "\">Activate Now</a> </p></blockquote>\n Link will expire in 15 minutes. <p>See you soon</p>" +
                "        \n" +
                "      </td>\n" +
                "      <td width=\"10\" valign=\"middle\"><br></td>\n" +
                "    </tr>\n" +
                "    <tr>\n" +
                "      <td height=\"30\"><br></td>\n" +
                "    </tr>\n" +
                "  </tbody></table><div class=\"yj6qo\"></div><div class=\"adL\">\n" +
                "\n" +
                "</div></div>";
    }

}
