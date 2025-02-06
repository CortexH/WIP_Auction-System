package com.demo.domain.bid;

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
@Table(name = "bids")

public class Bid {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID bid_id;

    @ManyToOne
    @JoinColumn(name = "auction_id")
    private Auction auction_id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user_id;

    private BigDecimal amount;

    private LocalDateTime timeStamp;

}
