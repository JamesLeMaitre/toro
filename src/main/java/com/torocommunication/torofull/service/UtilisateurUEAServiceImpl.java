package com.torocommunication.torofull.service;

import com.torocommunication.torofull.entities.RoleUEA;
import com.torocommunication.torofull.entities.UtilisateurUEA;
import com.torocommunication.torofull.repo.RoleUEARepo;
import com.torocommunication.torofull.repo.UtilisateurUEARepo;
import com.torocommunication.torofull.service.serviceInterface.UtilisateurUEAInterface;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
@Transactional
public class UtilisateurUEAServiceImpl implements UtilisateurUEAInterface, UserDetailsService {
    private final UtilisateurUEARepo userRepo;
    private final RoleUEARepo roleRepo;
    private  final PasswordEncoder passwordEncoder;

    public UtilisateurUEAServiceImpl(UtilisateurUEARepo userRepo, RoleUEARepo roleRepo, PasswordEncoder passwordEncoder) {
        this.userRepo = userRepo;
        this.roleRepo = roleRepo;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UtilisateurUEA user=userRepo.findByUsername(username);

        if(user == null){

            System.out.println("l'utilisateur n'existe pas dans la base");
            throw new UsernameNotFoundException("l'utilisateur n'existe pas dans la base ");
        }else {
            System.out.println("l'utilisateur est dans la base de données: {} "+username);

        }

        Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
        user.getRoleUEAS().forEach(role -> {
            authorities.add(new SimpleGrantedAuthority(role.getRolename()));
        });
        return new org.springframework.security.core.userdetails.User(user.getUsername(),user.getPassword(),authorities);
    }

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
    public RoleUEA saveRole(RoleUEA role) {
        System.out.println("enregistrement du role {} dans la base de données"+role.getRolename());
        return roleRepo.save(role);

    }

    @Override
    public void addRoleToUser(String username, String roleName) {
        System.out.println("enregistrement du role {} a  l'utilisateur {} "+roleName+username);
        UtilisateurUEA user= userRepo.findByUsername(username);
        RoleUEA role=roleRepo.findByName(roleName);
        user.getRoleUEAS().add(role);

    }

    @Override
    public UtilisateurUEA getUser(String username) {
        System.out.println("recherche de  l'utilisateur {} "+username);
        return userRepo.findByUsername(username);
    }

    @Override
    public List<UtilisateurUEA> getUsers() {
        System.out.println("recherche de tout les utilisateurs ");
        return userRepo.findAll();
    }

}
