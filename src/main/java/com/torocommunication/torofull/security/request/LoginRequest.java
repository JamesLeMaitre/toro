package com.torocommunication.torofull.security.request;

import com.sun.istack.NotNull;
import lombok.Data;
import lombok.RequiredArgsConstructor;



@RequiredArgsConstructor
@Data
public class LoginRequest {
    @NotNull
    private String username;

    @NotNull
    private String password;
}
