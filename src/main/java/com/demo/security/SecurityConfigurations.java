package com.demo.security;

import com.demo.configurations.EndpointsInformation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Component;

@Component
@EnableWebSecurity
@Slf4j
public class SecurityConfigurations {

    @Autowired
    private com.demo.security.AuthenticationFilter AuthenticationFilter;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity
                .cors(cors -> cors.disable())
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(req -> req
                        .requestMatchers("/h2-console/**").permitAll()
                        .requestMatchers(EndpointsInformation.NoAuthorizationRequiredEndpoints).permitAll()
                        .requestMatchers(EndpointsInformation.AnyAuthorizationNeeded).hasAnyRole("ADMIN", "AUCTIONEER", "BUYER")
                        .requestMatchers(EndpointsInformation.AuctioneerAuthorizationRequiredEndpoints).hasAnyRole("ADMIN", "AUCTIONEER")
                        .requestMatchers(EndpointsInformation.BuyerAuthorizationRequiredEndpoints).hasAnyRole("BUYER", "ADMIN", "AUCTIONEER")
                )
                .headers(header -> header.frameOptions(frame -> frame.disable()))
                .addFilterBefore(AuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
                .exceptionHandling(exp -> exp.accessDeniedHandler((request, response, accessDeniedException) -> {
                    response.getWriter().write("Access denied");
                    response.setStatus(401);
                    response.getWriter().flush();
                }))
                .build();
    }

}
