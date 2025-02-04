package com.demo.repositories;

import com.demo.domain.userHistory.UserHistory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface UserHistoryRepository extends JpaRepository<UserHistory, UUID> {
}
