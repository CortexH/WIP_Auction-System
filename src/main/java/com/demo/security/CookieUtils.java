package com.demo.security;

import com.demo.exceptions.NotAuthorizedException;
import jakarta.servlet.http.Cookie;
import lombok.extern.slf4j.Slf4j;

import java.nio.file.AccessDeniedException;

@Slf4j
public class CookieUtils {

    public static Cookie getCookieFromArray(Cookie[] cookies) {

        if(cookies == null) throw new NotAuthorizedException("Unauthorized access");
        for(Cookie cookie : cookies){
            if(cookie.getName().equals("AUTHORIZATION")){
                return cookie;
            }
        }
        throw new NotAuthorizedException("Unauthorized access");
    }

    public static Cookie createNewCookie(String name, String info, Integer time){

        Cookie cookie = new Cookie(name, info);
        cookie.setSecure(true);
        cookie.setMaxAge(time);
        cookie.setHttpOnly(true);
        cookie.setPath("/");

        return cookie;
    }

}
