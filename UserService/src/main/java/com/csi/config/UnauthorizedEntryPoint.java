package com.csi.config;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class UnauthorizedEntryPoint implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException {
         ResponseEntity.status(HttpServletResponse.SC_UNAUTHORIZED).body("Please Log In" + authException.getMessage());
        response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Please Log In" + authException.getMessage());
    }
}
