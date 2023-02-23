package com.torocommunication.torofull.controller;


import com.torocommunication.torofull.security.exceptions.RoleNotFoundException;
import com.torocommunication.torofull.security.request.LoginRequest;
import com.torocommunication.torofull.security.request.RegisterRequest;
import com.torocommunication.torofull.security.response.AppUserResponseStagiaire;
import com.torocommunication.torofull.security.response.JwtResponse;
import com.torocommunication.torofull.service.serviceInterface.UtilisateurUEAInterface;
import com.torocommunication.torofull.utiles.DataFormatter;
import  com.torocommunication.torofull.security.request.ResetPasswordRequest;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;


import javax.mail.MessagingException;
import java.io.PrintWriter;
import java.io.StringWriter;


@RestController
@AllArgsConstructor
@RequestMapping("api/auth/")
@CrossOrigin("*")
public class AuthRController extends DataFormatter<AppUserResponseStagiaire> {
    private final UtilisateurUEAInterface userService;
    private final AuthenticationManager authenticationManager;



    @PostMapping("register")
    public Object register( @RequestBody  RegisterRequest registerRequest) throws MessagingException, RoleNotFoundException {

        try {
            if (registerRequest.getTypeUEA() == 1 || registerRequest.getTypeUEA() == 2 ){
                return  renderData(true,userService.storeUser(registerRequest),"Operation successfully ");
            }else {
                return null;
            }


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
    public Object remember(@RequestBody() AppUserResponseStagiaire me){
        try {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            AppUserResponseStagiaire response = userService.authUser(authentication);

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
            AppUserResponseStagiaire response = userService.updateUser(request, authentication);
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


    @PutMapping("reset/{username}")
    public Object updatePassword(@RequestBody ResetPasswordRequest request, @PathVariable String username){
        try {
            AppUserResponseStagiaire response = userService.resetPassword(request, username);
            return  renderData(true,response,"Operation successfully ");
        } catch (Exception e) {

            return  renderStringData(false,"Error while processing" ,"verifier les informations entrer");
        }
    }



    @GetMapping("disabled/{username}/account")
    public Object disabled(@PathVariable String username){
        try {
            AppUserResponseStagiaire response = userService.disabledAccount(username);
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
            AppUserResponseStagiaire response = userService.enabledAccount(username);
            return  renderData(true,response,"Operation successfully ");
        } catch (Exception e) {
            StringWriter sw = new StringWriter();
            e.printStackTrace(new PrintWriter(sw));
            String exceptionAsString = sw.toString();
            return  renderStringData(false,"Error while processing" ,exceptionAsString);
        }
    }
}
