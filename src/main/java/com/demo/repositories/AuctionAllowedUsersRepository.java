package com.demo.repositories;

import com.demo.domain.auctionAllowedUsers.AuctionAllowedUsers;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.UUID;

public interface AuctionAllowedUsersRepository extends JpaRepository<AuctionAllowedUsers, UUID> {

    @Query(nativeQuery = true, value =
            "SELECT a.* from auction_allowed_users a " +
                    "WHERE a.related_auction = :id"
    )
    List<AuctionAllowedUsers> findAllByAuctionIdQuery(@Param("id") UUID id);

}
