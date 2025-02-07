package com.demo.services;

import com.demo.domain.user.User;
import com.demo.domain.user.UserRoles;
import com.demo.dto.input.LoginUserDTO;
import com.demo.dto.input.NewUserDTO;
import com.demo.dto.output.GenericSuccessDTO;
import com.demo.exceptions.ConflictException;
import com.demo.repositories.UserRepository;
import com.demo.utils.CookieUtils;
import com.demo.security.JwtUtils;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.NoSuchElementException;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final JwtUtils jwtUtils;
    private final TokenBlacklistService tokenBlacklistService;

    public GenericSuccessDTO registerNewUser(NewUserDTO data, HttpServletResponse response){

        if(userRepository.existsByEmail(data.email())) throw new ConflictException("There is already a user with specified email");

        User user = User.builder()
                .creation_date(LocalDateTime.now())
                .email(data.email())
                .password(data.password())
                .username(data.username())
                .roles(UserRoles.ADMIN)
                .purse(new BigDecimal(15000))
                .build();

        User saved_user = userRepository.save(user);

        String token = jwtUtils.createNewToken(saved_user.getEmail());

        Cookie cookie = CookieUtils.createNewCookie("AUTHORIZATION", token, 60*60*24*30);

        response.addCookie(cookie);

        return new GenericSuccessDTO(LocalDateTime.now(), 200, token);
    }

    public GenericSuccessDTO loginWithUser(LoginUserDTO data, HttpServletResponse response) {
        if(!userRepository.existsByEmailAndPassword(data.email(), data.password())) throw new NoSuchElementException("Email or password incorrect");

        String token = jwtUtils.createNewToken(data.email());

        Cookie cookie = CookieUtils.createNewCookie("AUTHORIZATION", token, 60 * 60 * 24 * 30);
        response.addCookie(cookie);

        return new GenericSuccessDTO(LocalDateTime.now(), 200, token);
    }

    public GenericSuccessDTO logoutUser(HttpServletRequest request, HttpServletResponse response){

        Cookie cookie = CookieUtils.getCookieFromArray(request.getCookies());
        String token = cookie.getValue();
        if(token.isBlank()) throw new ConflictException("Token is null");
        if(tokenBlacklistService.validateIfTokenIsNotBlacklisted(token)) throw new ConflictException("token is already blacklisted");

        tokenBlacklistService.blacklistToken(token);

        Cookie resp_cookie = CookieUtils.createNewCookie("AUTHORIZATION", null, 1);

        response.addCookie(resp_cookie);

        return new GenericSuccessDTO(LocalDateTime.now(), 200, "Successfully logged out user");

    }

    public User getUserByToken(String token){

        String email = jwtUtils.getTokenSubject(token);

        return userRepository.findByEmail(email)
                .orElseThrow(() -> new NoSuchElementException("User not found"));
    }

    public User findUserByHttpServletRequest(HttpServletRequest request){
        Cookie cookie = CookieUtils.getCookieFromArray(request.getCookies());
        String token = cookie.getValue();
        return getUserByToken(token);
    }


}
