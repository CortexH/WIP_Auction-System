package com.demo.repositories;

import com.demo.domain.auction.Auction;
import com.demo.domain.user.User;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public interface AuctionRepository extends JpaRepository<Auction, UUID> {

    @Query(nativeQuery = true, value =
            "SELECT a.* FROM auctions a " +
            "WHERE a.end_time <= :dateTime " +
            "AND a.status != 'CLOSED' " +
            "AND a.status != 'CANCELED'"
    )
    List<Auction> findAllByLessDateTimeThanParam(@Param(value = "dateTime")LocalDateTime dateTime);

    @Transactional
    @Modifying
    @Query(nativeQuery = true, value =
            "UPDATE auctions a " +
            "SET a.status = 'CLOSED' " +
            "WHERE a.auction_id IN :ids"
    )
    void closeAuctionsByUUIDList(@Param(value = "ids") List<UUID> ids);

    @Query(nativeQuery = true, value =
            "SELECT a.* FROM auctions a " +
            "WHERE a.owner_id = :userId"
    )
    List<Auction> findAuctionByUserNativeQuery(@Param("userId") UUID userId);

    @Transactional
    @Modifying
    @Query(nativeQuery = true, value =
            "UPDATE auctions a " +
                    "SET a.status = 'CANCELED' " +
                    "WHERE a.auction_id = :id"
    )
    void manuallyCancelAuction(@Param("id") UUID id);

}
