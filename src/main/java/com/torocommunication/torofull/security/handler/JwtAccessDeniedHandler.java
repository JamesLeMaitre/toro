package com.torocommunication.torofull.security.handler;


import com.torocommunication.torofull.security.filters.JwtAuthenticationEntryPoint;
import com.torocommunication.torofull.security.response.HttpErrorResponse;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


import static com.torocommunication.torofull.security.utils.constants.JavaConstant.ACCESS_DENIED_MESSAGE;
import static org.springframework.http.HttpStatus.UNAUTHORIZED;

@Component
public class JwtAccessDeniedHandler implements AccessDeniedHandler {
    @Override
    public void handle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AccessDeniedException e) throws IOException, ServletException {
        HttpErrorResponse errorResponse = new HttpErrorResponse();
        errorResponse.setStatus(UNAUTHORIZED);
        errorResponse.setStatusCode(UNAUTHORIZED.value());
        errorResponse.setReason(UNAUTHORIZED.getReasonPhrase());
        JwtAuthenticationEntryPoint.setMessage(httpServletResponse, errorResponse, ACCESS_DENIED_MESSAGE);
    }
}
