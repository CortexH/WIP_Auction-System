package com.demo.security;

import jakarta.servlet.http.Cookie;

import java.nio.file.AccessDeniedException;

public class CookieUtils {

    public static Cookie getCookieFromArray(Cookie[] cookies) throws AccessDeniedException {
        if(cookies == null) throw new AccessDeniedException("Unauthorized access");
        for(Cookie cookie : cookies){
            if(cookie.getName().equals("AUTHORIZATION")){
                return cookie;
            }
        }
        throw new AccessDeniedException("Unauthorized access");
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
