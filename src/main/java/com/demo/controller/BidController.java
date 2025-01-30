package com.demo.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/bids")
public class BidController {

    // create new bid
    @PostMapping
    public ResponseEntity<?> createBid(){
        return ResponseEntity.ok("A");
    }

    // list all bids in an auction by id
    @GetMapping("/{auction_id}")
    public ResponseEntity<?> listAllBidsInAuction(@PathVariable(name = "auction_id") String id){
        return ResponseEntity.ok("A");
    }

    // get all user bids
    @GetMapping("/users/{user_id}")
    public ResponseEntity<?> bidsUserHistory(@PathVariable(name = "user_id") String id){
        return ResponseEntity.ok("A");
    }

}
