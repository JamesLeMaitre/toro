package com.torocommunication.torofull.security.response;


import com.torocommunication.torofull.entities.RoleUEA;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.Collection;

@Data
@RequiredArgsConstructor
public class AppUserResponse {
    private String username;

    private String email;

    private Boolean isNotLocked;

    private Boolean isActive;

    private Collection<RoleUEA> roles;
}
