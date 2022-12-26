package com.torocommunication.torofull.service.security;

import com.torocommunication.torofull.entities.security.AppRole;
import com.torocommunication.torofull.entities.security.AppUsers;
import com.torocommunication.torofull.security.exceptions.PasswordNotMatchException;
import com.torocommunication.torofull.security.exceptions.RoleNotFoundException;
import com.torocommunication.torofull.security.request.LoginRequest;
import com.torocommunication.torofull.security.request.RegisterRequest;
import com.torocommunication.torofull.security.request.ResetPasswordRequest;
import com.torocommunication.torofull.security.response.AppUserResponse;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetailsService;

import javax.mail.MessagingException;
import java.util.List;

public interface AppUsersService extends UserDetailsService {
    String authenticate(LoginRequest request, AuthenticationManager authenticationManager);

    AppUserResponse authUser(Authentication authentication);

    AppUserResponse storeUser(RegisterRequest registerRequest) throws RoleNotFoundException, MessagingException;

    AppUserResponse updateUser(RegisterRequest request, Authentication authentication);

    void removeUser(String username);

    AppUserResponse resetPassword(ResetPasswordRequest request, String username) throws PasswordNotMatchException;

    AppUserResponse disabledAccount(String username);

    AppUserResponse enabledAccount(String username);

    AppRole saveRole(AppRole role);

    List<AppUsers> getAll();
}
