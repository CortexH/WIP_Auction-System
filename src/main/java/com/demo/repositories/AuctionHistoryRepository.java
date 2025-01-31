package com.demo.repositories;

import com.demo.domain.auctionHistory.AuctionHistory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface AuctionHistoryRepository extends JpaRepository<AuctionHistory, UUID> {
}
