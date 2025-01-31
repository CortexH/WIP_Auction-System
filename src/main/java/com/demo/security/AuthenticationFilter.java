package com.demo.security;

import com.demo.configurations.EndpointsInformation;
import com.demo.domain.user.User;
import com.demo.services.UserService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.nio.file.AccessDeniedException;
import java.util.Arrays;
import java.util.List;

@Component
public class AuthenticationFilter extends OncePerRequestFilter {

    private JwtUtils jwtUtils;

    @Autowired
    private UserService userService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        try{
            if(returnTrueIfNoAuthorizationAccessEndpoint(request.getRequestURI())){
                filterChain.doFilter(request, response);
                return;
            }

            Cookie[] array_of_cookies = request.getCookies();
            Cookie cookie = CookieUtils.getCookieFromArray(array_of_cookies);

            String token = cookie.getValue();
            User user = userService.getUserByToken(token);

            CustomUserDetails details = new CustomUserDetails(user);
            Authentication auth = new UsernamePasswordAuthenticationToken(user.getEmail(), null, details.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(auth);

            filterChain.doFilter(request, response);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    // return true if the requested endpoint has no required authorization
    private boolean returnTrueIfNoAuthorizationAccessEndpoint(String endpoint){
        if(Arrays.asList(EndpointsInformation.NoAuthorizationRequiredEndpoints).contains(endpoint)) return true;
        return false;
    }

}
