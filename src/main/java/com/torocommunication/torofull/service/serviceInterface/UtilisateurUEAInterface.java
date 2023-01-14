package com.torocommunication.torofull.service.serviceInterface;

import com.torocommunication.torofull.entities.RoleUEA;
import com.torocommunication.torofull.entities.UtilisateurUEA;
import com.torocommunication.torofull.security.exceptions.PasswordNotMatchException;
import com.torocommunication.torofull.security.exceptions.RoleNotFoundException;
import com.torocommunication.torofull.security.request.LoginRequest;
import com.torocommunication.torofull.security.request.RegisterRequest;
import com.torocommunication.torofull.security.request.ResetPasswordRequest;
import com.torocommunication.torofull.security.response.AppUserReponseDem;
import com.torocommunication.torofull.security.response.AppUserResponseStagiaire;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;

import javax.mail.MessagingException;
import java.util.List;

public interface UtilisateurUEAInterface {

    UtilisateurUEA saveUser(UtilisateurUEA user);

    UtilisateurUEA getByID(Long id);
    RoleUEA saveRole(RoleUEA role);
    void addRoleToUser(String username,String roleName) throws RoleNotFoundException;
    UtilisateurUEA getByUsername(String username);
    List<UtilisateurUEA> getUsers();


    String authenticate(LoginRequest request, AuthenticationManager authenticationManager);

    AppUserResponseStagiaire authUser(Authentication authentication);

    AppUserResponseStagiaire storeUser(RegisterRequest registerRequest) throws RoleNotFoundException, MessagingException;


    AppUserReponseDem storeUserDem(RegisterRequest registerRequest) throws RoleNotFoundException, MessagingException;

    AppUserResponseStagiaire updateUser(RegisterRequest request, Authentication authentication);

    void removeUser(String username);

    AppUserResponseStagiaire resetPassword(ResetPasswordRequest request, String username) throws PasswordNotMatchException, PasswordNotMatchException;

    AppUserResponseStagiaire disabledAccount(String username);

    AppUserResponseStagiaire enabledAccount(String username);


    UtilisateurUEA findByUsernameAndPassword (String username,String password);


    int count();

    int countAdmin();


}
