package com.demo.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class AuctionHistoryController {

    // return the history of any specific auction
    @GetMapping("/auctions/{id}/history")
    public ResponseEntity<?> getAuctionHistoryById(@PathVariable(name = "id") String id){
        return ResponseEntity.ok("A");
    }

    // return all the bids of any user
    @GetMapping("/users/{id}/bids")
    public ResponseEntity<?> getAllUserBids(@PathVariable(name = "id") String id){
        return ResponseEntity.ok("A");
    }

    // return all the winned or created actions by any user
    @GetMapping("/users/{id}/auctions")
    public ResponseEntity<?> getAllUserCreatedAuctions(@PathVariable(name = "id") String id){
        return ResponseEntity.ok("A");
    }

    // return a list of bidders in any auction
    @GetMapping("/auctions/{id}/bidders")
    public ResponseEntity<?> getAllAuctionBidders(@PathVariable(name = "id") String id){
        return ResponseEntity.ok("a");
    }
}


