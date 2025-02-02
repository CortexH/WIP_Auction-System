package com.demo.controller;

import com.demo.dto.input.NewAuctionDTO;
import com.demo.services.AuctionService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auctions")
public class AuctionsController {

    @Autowired
    private AuctionService auctionService;

    // create new auction - authentication required
    @PostMapping("/new")
    public ResponseEntity<?> createNewAuction(
            HttpServletRequest request,
            @RequestBody(required = true) NewAuctionDTO data
            ){
        return ResponseEntity.ok(auctionService.createNewAuction(data, request));
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
