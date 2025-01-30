package com.demo.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class AuthenticationController {

    // register user
    @PostMapping("/register")
    public ResponseEntity<?> registerNewUser(){
        return ResponseEntity.ok("a");
    }

    // login with user
    @PostMapping("/login")
    public ResponseEntity<?> loginWithUser(){

        return ResponseEntity.ok("a");
    }

    // get any user by id
    @GetMapping("/{id}")
    public ResponseEntity<?> getUserById(@PathVariable(name = "id") String id){

        return ResponseEntity.ok("a");
    }

}
