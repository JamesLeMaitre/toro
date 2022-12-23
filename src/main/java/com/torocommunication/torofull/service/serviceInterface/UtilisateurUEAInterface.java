package com.torocommunication.torofull.service.serviceInterface;

import com.torocommunication.torofull.entities.RoleUEA;
import com.torocommunication.torofull.entities.UtilisateurUEA;
import com.torocommunication.torofull.security.exceptions.PasswordNotMatchException;
import com.torocommunication.torofull.security.exceptions.RoleNotFoundException;
import com.torocommunication.torofull.security.request.LoginRequest;
import com.torocommunication.torofull.security.request.RegisterRequest;
import com.torocommunication.torofull.security.request.ResetPasswordRequest;
import com.torocommunication.torofull.security.response.AppUserResponse;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;

import javax.mail.MessagingException;
import java.util.List;

public interface UtilisateurUEAInterface {

    UtilisateurUEA saveUser(UtilisateurUEA user);

    UtilisateurUEA getByID(Long id);
    RoleUEA saveRole(RoleUEA role);
    void addRoleToUser(String username,String roleName) throws RoleNotFoundException;
    UtilisateurUEA getUser(String username);
    List<UtilisateurUEA> getUsers();


    String authenticate(LoginRequest request, AuthenticationManager authenticationManager);

    AppUserResponse authUser(Authentication authentication);

    AppUserResponse storeUser(RegisterRequest registerRequest) throws RoleNotFoundException, MessagingException;

    AppUserResponse updateUser(RegisterRequest request, Authentication authentication);

    void removeUser(String username);

    AppUserResponse resetPassword(ResetPasswordRequest request, String username) throws PasswordNotMatchException, PasswordNotMatchException;

    AppUserResponse disabledAccount(String username);

    AppUserResponse enabledAccount(String username);


}
