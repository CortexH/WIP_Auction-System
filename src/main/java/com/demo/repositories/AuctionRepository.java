package com.demo.repositories;

import com.demo.domain.auction.Auction;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface AuctionRepository extends JpaRepository<Auction, UUID> {
}
