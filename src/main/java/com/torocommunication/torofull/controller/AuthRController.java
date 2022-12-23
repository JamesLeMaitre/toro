package com.torocommunication.torofull.controller;


import com.torocommunication.torofull.security.request.LoginRequest;
import com.torocommunication.torofull.security.request.RegisterRequest;
import com.torocommunication.torofull.security.response.AppUserResponse;
import com.torocommunication.torofull.security.response.JwtResponse;
import com.torocommunication.torofull.service.serviceInterface.UtilisateurUEAInterface;
import com.torocommunication.torofull.utiles.DataFormatter;
import  com.torocommunication.torofull.security.request.ResetPasswordRequest;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;


import java.io.PrintWriter;
import java.io.StringWriter;

import static com.torocommunication.torofull.security.utils.constants.JavaConstant.API_BASE_URL;


@RestController
@AllArgsConstructor
@RequestMapping(API_BASE_URL)
public class AuthRController extends DataFormatter<AppUserResponse> {
    private final UtilisateurUEAInterface userService;
    private final AuthenticationManager authenticationManager;



    @PostMapping("register")
    public Object register(@RequestBody  RegisterRequest registerRequest){
        try {
            return  renderData(true, userService.storeUser(registerRequest),"Create ");
        } catch (Exception e) {
            StringWriter sw = new StringWriter();
            e.printStackTrace(new PrintWriter(sw));
            String exceptionAsString = sw.toString();
            return  renderStringData(false,"Error while processing" ,exceptionAsString);
        }

    }

   @PostMapping("login")
    public Object login(@RequestBody() LoginRequest loginRequest){
        try {
            String token = userService.authenticate(loginRequest, authenticationManager);
            JwtResponse response = new JwtResponse();
            response.setAccess_token(token);
            String s = response.getAccess_token();
            return  renderStringData(true,s,"Create ");
        } catch (Exception e) {
            StringWriter sw = new StringWriter();
            e.printStackTrace(new PrintWriter(sw));
            String exceptionAsString = sw.toString();
            return  renderStringData(false,"Error while processing" ,exceptionAsString);
        }

    }


    @PostMapping("me")
    public Object remember(@RequestBody() AppUserResponse me){
        try {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            AppUserResponse response = userService.authUser(authentication);

            return  renderData(true,response,"Create ");
        } catch (Exception e) {
            StringWriter sw = new StringWriter();
            e.printStackTrace(new PrintWriter(sw));
            String exceptionAsString = sw.toString();
            return  renderStringData(false,"Error while processing" ,exceptionAsString);
        }

    }



    @PutMapping(value = "edit/account")
    public Object update( @RequestBody RegisterRequest request) {
        try {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            AppUserResponse response = userService.updateUser(request, authentication);
            if( authentication.getName() ==null){
                return  renderStringData(false,"No User" ,"item not found");
            }
            return  renderData(true, response,"update done successfully");
        } catch (Exception e) {
            StringWriter sw = new StringWriter();
            e.printStackTrace(new PrintWriter(sw));
            String exceptionAsString = sw.toString();
            return  renderStringData(false,"Error while processing" ,exceptionAsString);
        }
    }




    @DeleteMapping("delete/{username}/account")
    public Object delete(@PathVariable String username){
        try {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

            if(authentication.getName() == null){
                return  renderStringData(false,"No User to delete" ,"item not found");
            }
            userService.removeUser(username);
            return  renderStringData(true, "Delete successfully","Done");
        } catch (Exception e) {
            StringWriter sw = new StringWriter();
            e.printStackTrace(new PrintWriter(sw));
            String exceptionAsString = sw.toString();
            return  renderStringData(false,"Error while processing" ,exceptionAsString);
        }
    }


    @PutMapping("reset/{username}/password")
    public Object updatePassword(@RequestBody ResetPasswordRequest request, @PathVariable String username){
        try {
            AppUserResponse response = userService.resetPassword(request, username);
            return  renderData(true,response,"Operation successfully ");
        } catch (Exception e) {
            StringWriter sw = new StringWriter();
            e.printStackTrace(new PrintWriter(sw));
            String exceptionAsString = sw.toString();
            return  renderStringData(false,"Error while processing" ,exceptionAsString);
        }
    }



    @GetMapping("disabled/{username}/account")
    public Object disabled(@PathVariable String username){
        try {
            AppUserResponse response = userService.disabledAccount(username);
            return  renderData(true,response,"Operation successfully ");
        } catch (Exception e) {
            StringWriter sw = new StringWriter();
            e.printStackTrace(new PrintWriter(sw));
            String exceptionAsString = sw.toString();
            return  renderStringData(false,"Error while processing" ,exceptionAsString);
        }
    }



    @GetMapping("enabled/{username}/account")
    public Object getResponse(@PathVariable String username){
        try {
            AppUserResponse response = userService.enabledAccount(username);
            return  renderData(true,response,"Operation successfully ");
        } catch (Exception e) {
            StringWriter sw = new StringWriter();
            e.printStackTrace(new PrintWriter(sw));
            String exceptionAsString = sw.toString();
            return  renderStringData(false,"Error while processing" ,exceptionAsString);
        }
    }
}
