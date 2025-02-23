package com.demo.controller;

import com.demo.dto.input.NewAuctionDTO;
import com.demo.services.AuctionService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.websocket.server.PathParam;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/auctions")
@RequiredArgsConstructor
public class AuctionsController {

    private final AuctionService auctionService;

    // create new auction - authentication required
    @PostMapping("/new")
    public ResponseEntity<?> createNewAuction(
            HttpServletRequest request,
            @RequestBody(required = true) NewAuctionDTO data
            ){
        return ResponseEntity.ok(auctionService.createNewAuction(data, request));
    }

    // list all user auctions - authentication required
    @GetMapping("/list")
    public ResponseEntity<?> listAllAuctions(
            HttpServletRequest request
    ){
        return ResponseEntity.ok(auctionService.findAllAuctionsFromUser(request));
    }

    // get auction by id - authentication required
    @GetMapping("/find")
    public ResponseEntity<?> getAuctionById(
            @PathParam(value = "id") UUID id,
            @PathParam(value = "history") Boolean history,
            HttpServletRequest request
    ){
        if(!history){
            return ResponseEntity.ok(auctionService.findAuction_OrAuctionAndHistory(request, id));
        }
        return ResponseEntity.ok(auctionService.findAuction_OrAuctionAndHistory(request, id, true));
    }

    // manually close auction - authentication required, only by creator user / admin role required
    @PutMapping("/{id}/close")
    public ResponseEntity<?> manuallyCloseAuction(@PathVariable(name = "id") String id){
        return ResponseEntity.ok("A");
    }

    // cancel auction - authentication required, only by creator user / admin role required
    // can only be requested if auction has not started
    @PutMapping("/cancel/{id}")
    public ResponseEntity<?> cancelAuction(
            @PathVariable(name = "id") UUID id,
            HttpServletRequest request
    ){
        return ResponseEntity.ok(auctionService.manuallyCancelAuction(request, id));
    }

}
