package com.demo.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auctions")
public class AuctionsController {

    // create new auction - authentication required
    @PostMapping("/new")
    public ResponseEntity<?> createNewAuction(){
        return ResponseEntity.ok("A");
    }

    // list all auctions - authentication required
    @GetMapping("/list")
    public ResponseEntity<?> listAllAuctions(){
        return ResponseEntity.ok("A");
    }

    // get auction by id - authentication required
    @GetMapping("/{id}")
    public ResponseEntity<?> getAuctionById(@PathVariable(name = "id") String id){
        return ResponseEntity.ok("A");
    }

    // manually close auction - authentication required, only by creator user / admin role required
    @PutMapping("/{id}/close")
    public ResponseEntity<?> manuallyCloseAuction(@PathVariable(name = "id") String id){
        return ResponseEntity.ok("A");
    }

    // cancel auction - authentication required, only by creator user / admin role required
    @DeleteMapping("/{id}")
    public ResponseEntity<?> cancelAuction(@PathVariable(name = "id") String id){
        return ResponseEntity.ok("A");
    }

}
