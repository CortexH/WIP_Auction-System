package com.demo.controller;

import com.demo.dto.input.LoginUserDTO;
import com.demo.dto.input.NewUserDTO;
import com.demo.security.JwtUtils;
import com.demo.services.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.nio.file.AccessDeniedException;
import java.util.Arrays;

@RestController
@RequestMapping("/user")
public class AuthenticationController {

    @Autowired
    private UserService userService;

    // register user
    @PostMapping("/register")
    public ResponseEntity<?> registerNewUser(
            @RequestBody(required = true) NewUserDTO data,
            HttpServletResponse response
            ){
        return ResponseEntity.ok(userService.registerNewUser(data, response));
    }

    // login with user
    @PostMapping("/login")
    public ResponseEntity<?> loginWithUser(
            @RequestBody(required = true) LoginUserDTO data,
            HttpServletResponse response
    ){
        return ResponseEntity.ok(userService.loginWithUser(data, response));
    }

    // get any user by id (basically allow you to see someone account when you click in his profile.
    // e.g: highest bid, total bids, total values bids, auctions wins, etc.
    // maybe ill not add this endpoint
    @GetMapping("/{id}")
    public ResponseEntity<?> getUserById(@PathVariable(name = "id") String id){

        return ResponseEntity.ok("a");
    }

}
