package com.demo.repositories;

import com.demo.domain.tokenBlacklist.TokenBlacklist;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TokenBlacklistRepository extends JpaRepository<TokenBlacklist, Long> {
}
