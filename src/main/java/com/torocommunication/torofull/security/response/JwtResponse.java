package com.torocommunication.torofull.security.response;


import com.torocommunication.torofull.security.utils.constants.JwtConstant;
import lombok.Data;


@Data
public class JwtResponse {
    private String access_token;

    private String token_type = JwtConstant.TOKEN_PREFIX;

    private long expiration_time = JwtConstant.EXPIRATION_TIME;
}
