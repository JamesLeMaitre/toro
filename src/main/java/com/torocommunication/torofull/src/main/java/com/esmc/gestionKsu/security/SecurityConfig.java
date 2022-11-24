package com.torocommunication.torofull.src.main.java.com.esmc.gestionKsu.security;

import com.esmc.security.utils.BaseSecurityConfig;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

/**
 *
 * @author user
 */

@Configuration
@EnableWebSecurity
public class SecurityConfig extends BaseSecurityConfig {

}
