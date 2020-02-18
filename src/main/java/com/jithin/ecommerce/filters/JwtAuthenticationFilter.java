package com.jithin.ecommerce.filters;

import com.jithin.ecommerce.configuration.JwtTokenProvider;
import com.jithin.ecommerce.model.User;
import com.jithin.ecommerce.services.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collection;
import java.util.Collections;

import static com.jithin.ecommerce.configuration.SecurityConfig.HEADER_STRING;
import static com.jithin.ecommerce.configuration.SecurityConfig.TOKEN_PREFIX;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {
    @Autowired
    private JwtTokenProvider jwtTokenProvider;
    @Autowired
    private CustomUserDetailsService customUserDetailService;

    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {

        try {
            String token = getJwtFromRequest(httpServletRequest);

            if (StringUtils.hasText(token) && jwtTokenProvider.valid_token(token)) {

                String userId = jwtTokenProvider.getUserIdFromJwt(token);

                User user = customUserDetailService.loadUserById(userId);

                Collection<? extends GrantedAuthority> authorities =user.getAuthorities();
                UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                        user, null,authorities
                );
                authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(httpServletRequest));
                SecurityContextHolder.getContext().setAuthentication(authenticationToken);

            }


        } catch (Exception ex) {

            System.out.println("cannot set token on security context, filter");

        }

        filterChain.doFilter(httpServletRequest, httpServletResponse);
    }

    private String getJwtFromRequest(HttpServletRequest httpServletRequest) {

        String bearerToken = httpServletRequest.getHeader(HEADER_STRING);

        if (StringUtils.hasText(bearerToken) && bearerToken.startsWith(TOKEN_PREFIX)) {
            return bearerToken.substring(7, bearerToken.length());
        }

        return null;
    }
}
