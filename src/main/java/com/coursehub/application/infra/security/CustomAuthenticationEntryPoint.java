package com.coursehub.application.infra.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CustomAuthenticationEntryPoint implements AuthenticationEntryPoint {

    private final Logger logger = Logger.getLogger(CustomAuthenticationEntryPoint.class.getName());

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException {
        logger.log(Level.INFO, "Pre-authenticated entry point called. Rejecting access");

        response.setContentType("application/json");

        Map<String, String> responseError = new HashMap<>();
        responseError.put("error", "Você não está autorizado a acessar este recurso.");
        responseError.put("message", authException.getMessage());

        ObjectMapper mapper = new ObjectMapper();
        String jsonErrorResponse = mapper.writeValueAsString(responseError);
        response.getWriter().write(jsonErrorResponse);
    }
}
