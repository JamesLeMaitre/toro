package com.torocommunication.torofull.security.request;

import com.sun.istack.NotNull;
import lombok.Data;
import lombok.RequiredArgsConstructor;



@Data
@RequiredArgsConstructor
public class ResetPasswordRequest {

    @NotNull
    private String old_password;

    @NotNull
    private String new_password;

    @NotNull
    private String confirm_password;
}
