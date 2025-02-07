package com.demo.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.websocket.server.PathParam;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/auctions/allowlist")
public class AuctionAllowedUsersController {

    // allowed for default users
    @GetMapping("/get/user")
    public ResponseEntity<?> getUserAllowedAuctions(
            @PathParam(value = "userId") UUID id,
            HttpServletRequest request
    ){
        return ResponseEntity.ok("A");
    }

    @PostMapping("/add")
    public ResponseEntity<?> addToAllowlist(
            @PathParam(value = "userId") UUID user_id,
            @PathParam(value = "auctionId") UUID auction_id,
            HttpServletRequest request
            ){
        return ResponseEntity.ok("A");
    }

    @GetMapping("/get")
    public ResponseEntity<?> getAllowlistFromAuction(
            @PathParam(value = "auctionId") UUID id,
            HttpServletRequest request
    ){
        return ResponseEntity.ok("A");
    }

    @DeleteMapping("/remove")
    public ResponseEntity<?> removeFromAllowlist(
            @PathParam(value = "userId") UUID user_id,
            @PathParam(value = "auctionId") UUID auction_id,
            HttpServletRequest request
    ){
        return ResponseEntity.ok("A");
    }



}
