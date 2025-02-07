package com.demo.domain.auction;


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
@Table(name = "auctions")
public class Auction {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID auction_id;

    private String title;
    private String description;
    private BigDecimal starting_price;
    private BigDecimal current_price;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private AuctionStatus status;

    private LocalDateTime start_time;
    private LocalDateTime end_time;

    @ManyToOne
    @JoinColumn(name = "owner_id", nullable = false)
    private User owner_id;

    @ManyToOne
    @JoinColumn(name = "winner_id", nullable = true)
    private User winner_id;

    private LocalDateTime creation_date;

    @Enumerated(EnumType.STRING)
    private AuctionVisibility visibility;
}
