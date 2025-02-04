package com.demo.repositories;

import com.demo.domain.auction.Auction;
import com.demo.domain.auctionHistory.AuctionHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.UUID;

public interface AuctionHistoryRepository extends JpaRepository<AuctionHistory, UUID> {

    @Query(nativeQuery = true, value =
            "SELECT a.* FROM auctions_histories a " +
                    "WHERE a.auction_id == :id " +
                    "ORDER BY timeStamp DESC"
    )
    List<AuctionHistory> findAllByauctionNativeQuery(@Param(value = "id") UUID id);

    @Query(nativeQuery = true, value =
            "SELECT a.* FROM auctions_histories a " +
                    "WHERE a.event_type == 'BID_GIVEN' " +
                    "ORDER BY a.timeStamp DESC"
    )
    List<AuctionHistory> findAllBidsInAuctionOrdered(@Param("id") UUID id);

}
