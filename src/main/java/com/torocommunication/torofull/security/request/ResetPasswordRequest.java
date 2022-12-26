package com.torocommunication.torofull.security.request;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.persistence.Column;

@Data
@RequiredArgsConstructor
public class ResetPasswordRequest {

    @Column(nullable = false)
    private String old_password;

    @Column(nullable = false)
    private String new_password;

    @Column(nullable = false)
    private String confirm_password;
}
