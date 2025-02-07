package com.demo.domain.auctionAllowedUsers;

import com.demo.domain.auction.Auction;
import com.demo.domain.user.User;
import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Entity
@Table(name = "auction_allowed_users")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class AuctionAllowedUsers {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne
    @JoinColumn(nullable = false, name = "allowed_user")
    private User allowed_user;

    @ManyToOne
    @JoinColumn(nullable = false, name = "related_auction")
    private Auction auction;

}
