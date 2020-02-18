package com.jithin.ecommerce.configuration;

import com.google.gson.Gson;
import com.jithin.ecommerce.dto.InvalidResponse;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest httpServletRequest,
                         HttpServletResponse httpServletResponse, AuthenticationException e)
            throws IOException, ServletException {

        InvalidResponse invalidResponse = new InvalidResponse();
        String jsonResponse = new Gson().toJson(invalidResponse);

        httpServletResponse.setContentType("application/json");
        httpServletResponse.setStatus(401);
        httpServletResponse.getWriter().print(jsonResponse);
    }
}
