package com.torocommunication.torofull.controller;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.torocommunication.torofull.entities.RoleUEA;
import com.torocommunication.torofull.entities.UtilisateurUEA;
import com.torocommunication.torofull.service.serviceInterface.UtilisateurUEAInterface;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URI;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;
import static org.springframework.http.HttpStatus.FORBIDDEN;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class UserRessource {

 /*   private  final UtilisateurUEAInterface userService;



    @GetMapping(value = "/users")
    public ResponseEntity<List<UtilisateurUEA>>getUsers() {
        return  ResponseEntity.ok().body(userService.getUsers());
    }
    @PostMapping("/users/save")
    public ResponseEntity<UtilisateurUEA> saveUser(@RequestBody UtilisateurUEA user){
        URI uri= URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/user/save").toString());
        return  ResponseEntity.created(uri).body(userService.saveUser(user));
    }
    @PostMapping("/role/save")
    public ResponseEntity<RoleUEA> saveRole(@RequestBody RoleUEA role){
        URI uri= URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/role/save").toString());
        return  ResponseEntity.created(uri).body(userService.saveRole(role));
    }
    @PostMapping("/role/addtouser")
    public ResponseEntity<?> addRoleToUser(@RequestBody RoleToUserFrom from){
        userService.addRoleToUser(from.getUsername(),from.getRoleName());
        return  ResponseEntity.ok().build();
    }
    @GetMapping("/token/refresh")
    public void  refreshToken(HttpServletRequest request, HttpServletResponse response) throws IOException {

        String authorizationHeader=request.getHeader(AUTHORIZATION);
        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")){
            try {
                String refresh_token=authorizationHeader.substring("Bearer ".length());
                Algorithm algorithm=Algorithm.HMAC256("secret".getBytes());
                JWTVerifier verifier= JWT.require(algorithm).build();
                DecodedJWT decodedJWT = verifier.verify(refresh_token);
                String username=decodedJWT.getSubject();
                UtilisateurUEA user=userService.getUser(username);
                String access_token= JWT.create()
                        .withSubject(user.getUsername())
                        .withExpiresAt(new java.sql.Date(System.currentTimeMillis() +30*60*1000))
                        .withIssuer(request.getRequestURL().toString())
                        .withClaim("roles", user.getRoleUEAS().stream().map(RoleUEA::getRolename).collect(Collectors.toList()))
                        .sign(algorithm);
                Map<String,String> tokens= new HashMap<>();
                tokens.put("access_token",access_token);
                tokens.put("refresh_token",refresh_token);
                response.setContentType(APPLICATION_JSON_VALUE);
                new ObjectMapper().writeValue(response.getOutputStream(),tokens);


            }catch (Exception exception){
                response.setHeader("error",exception.getMessage());
                response.setStatus(FORBIDDEN.value());
                // response.sendError(FORBIDDEN.value());
                Map<String,String> error= new HashMap<>();
                error.put("error_message",exception.getMessage());
                response.setContentType(APPLICATION_JSON_VALUE);
                new ObjectMapper().writeValue(response.getOutputStream(),error);
            }

        }else {
            throw new RuntimeException("actualisation de token echoué");
        }
    }
*/
}
@Data
class RoleToUserFrom{
    private String username;
    private String roleName;

}
