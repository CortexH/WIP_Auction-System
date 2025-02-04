package com.demo.domain.userHistory;

import com.demo.domain.auction.Auction;
import com.demo.domain.user.User;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "user_histories")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID user_history_id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User userId;

    @ManyToOne
    @JoinColumn(name = "auction_id", nullable = true)
    private Auction auction;

    private BigDecimal amout;
    private String message;
    private LocalDateTime timeStamp;

}
