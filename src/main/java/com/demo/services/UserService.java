package com.demo.services;

import com.demo.domain.user.User;
import com.demo.domain.user.UserRoles;
import com.demo.dto.input.LoginUserDTO;
import com.demo.dto.input.NewUserDTO;
import com.demo.dto.output.GenericSuccessDTO;
import com.demo.repositories.UserRepository;
import com.demo.security.CookieUtils;
import com.demo.security.JwtUtils;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.nio.file.AccessDeniedException;
import java.time.LocalDateTime;
import java.util.NoSuchElementException;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;
    private JwtUtils jwtUtils;

    public GenericSuccessDTO registerNewUser(NewUserDTO data, HttpServletResponse response){

        User user = User.builder()
                .creation_date(LocalDateTime.now())
                .email(data.email())
                .password(data.password())
                .username(data.username())
                .roles(UserRoles.ADMIN)
                .build();

        User saved_user = userRepository.save(user);

        this.jwtUtils = new JwtUtils();
        String token = this.jwtUtils.createNewToken(saved_user.getEmail());

        Cookie cookie = CookieUtils.createNewCookie("AUTHORIZATION", token, 60*60*24*30);

        response.addCookie(cookie);

        return new GenericSuccessDTO(LocalDateTime.now(), 200, token);
    }

    public GenericSuccessDTO loginWithUser(LoginUserDTO data, HttpServletResponse response) {
        if(!userRepository.existsByEmailAndPassword(data.email(), data.password())) throw new NoSuchElementException("Email or password incorrect");

        this.jwtUtils = new JwtUtils();
        String token = this.jwtUtils.createNewToken(data.email());

        Cookie cookie = CookieUtils.createNewCookie("AUTHORIZATION", token, 60 * 60 * 24 * 30);
        response.addCookie(cookie);

        return new GenericSuccessDTO(LocalDateTime.now(), 200, token);
    }

    public User getUserByToken(String token){
        this.jwtUtils = new JwtUtils();
        String email = this.jwtUtils.getTokenSubject(token);

        return userRepository.findByEmail(email)
                .orElseThrow(() -> new NoSuchElementException("User not found"));
    }


}
