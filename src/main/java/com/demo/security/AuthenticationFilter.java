package com.demo.security;

import com.demo.configurations.EndpointsInformation;
import com.demo.domain.user.User;
import com.demo.dto.output.GenericErrorDTO;
import com.demo.exceptions.NotAuthorizedException;
import com.demo.services.TokenBlacklistService;
import com.demo.services.UserService;
import com.demo.utils.CookieUtils;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.NoSuchElementException;

@Component
@Slf4j
public class AuthenticationFilter extends OncePerRequestFilter {

    @Autowired
    private JwtUtils jwtUtils;

    @Autowired
    private UserService userService;

    @Autowired
    private TokenBlacklistService tokenBlacklistService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        try{
            if(
                    returnTrueIfNoAuthorizationAccessEndpoint(request.getRequestURI())
                    || request.getRequestURI().contains("/h2-console")
            ){
                filterChain.doFilter(request, response);
                return;
            }
            Cookie[] array_of_cookies = request.getCookies();
            Cookie cookie = CookieUtils.getCookieFromArray(array_of_cookies);

            String token = cookie.getValue();

            if(tokenBlacklistService.validateIfTokenIsNotBlacklisted(token)) throw new NotAuthorizedException("Not authorized");
            User user = userService.getUserByToken(token);

            CustomUserDetails details = new CustomUserDetails(user);
            Authentication auth = new UsernamePasswordAuthenticationToken(user.getEmail(), null, details.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(auth);

            filterChain.doFilter(request, response);
        } catch (NotAuthorizedException e) {

            GenericErrorDTO data = new GenericErrorDTO(
                    LocalDateTime.now().toString(),
                    401,
                    HttpStatus.UNAUTHORIZED.getReasonPhrase(),
                    "Not authorized"
                    );

            response.setStatus(401);
            response.getWriter().write(new ObjectMapper().writeValueAsString(data));
            response.setContentType("application/json");
            response.getWriter().flush();
        } catch (NoSuchElementException e) {
            GenericErrorDTO data = new GenericErrorDTO(
                    LocalDateTime.now().toString(),
                    401,
                    HttpStatus.UNAUTHORIZED.getReasonPhrase(),
                    "Incorrect password or email"
            );

            response.setStatus(401);
            response.getWriter().write(new ObjectMapper().writeValueAsString(data));
            response.setContentType("application/json");
            response.getWriter().flush();
        }

    }

    // return true if the requested endpoint has no required authorization
    private boolean returnTrueIfNoAuthorizationAccessEndpoint(String endpoint){
        if(Arrays.asList(EndpointsInformation.NoAuthorizationRequiredEndpoints).contains(endpoint)) return true;
        return false;
    }

}
