package com.demo.domain.auctionHistory;

import com.demo.domain.auction.Auction;
import com.demo.domain.user.User;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "auctions_histories")
public class AuctionHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID auction_history_id;

    @ManyToOne
    @JoinColumn(name = "auction_id", nullable = false)
    private Auction auction;

    @Enumerated(EnumType.STRING)
    private AuctionEventType event_type;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = true)
    private User user_id;

    private BigDecimal amout;

    private LocalDateTime timeStamp;
    private String message;

}
