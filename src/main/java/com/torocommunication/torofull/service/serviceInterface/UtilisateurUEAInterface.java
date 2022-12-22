package com.torocommunication.torofull.service.serviceInterface;

import com.torocommunication.torofull.entities.RoleUEA;
import com.torocommunication.torofull.entities.UtilisateurUEA;

import java.util.List;

public interface UtilisateurUEAInterface {

    UtilisateurUEA saveUser(UtilisateurUEA user);
    RoleUEA saveRole(RoleUEA role);
    void addRoleToUser(String username,String roleName);
    UtilisateurUEA getUser(String username);
    List<UtilisateurUEA> getUsers();
}
