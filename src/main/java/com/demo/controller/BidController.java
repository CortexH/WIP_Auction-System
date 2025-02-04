package com.demo.controller;

import com.demo.dto.input.NewBidDTO;
import com.demo.services.BidService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/bids")
@RequiredArgsConstructor
public class BidController {

    private final BidService bidService;

    // bid in an auction
    @PostMapping
    public ResponseEntity<?> createBid(
            HttpServletRequest request,
            @RequestBody NewBidDTO data
            ){
        return ResponseEntity.ok(bidService.bidAuction(request, data));
    }

    // list all bids in an auction by id
    @GetMapping("/{auction_id}")
    public ResponseEntity<?> listAllBidsInAuction(@PathVariable(name = "auction_id") String id){
        return ResponseEntity.ok(bidService.getAllBidsInAnAuction(UUID.fromString(id)));
    }

    // get all user bids
    @GetMapping("/users/{user_id}")
    public ResponseEntity<?> bidsUserHistory(@PathVariable(name = "user_id") String id){
        return ResponseEntity.ok("A");
    }

}
