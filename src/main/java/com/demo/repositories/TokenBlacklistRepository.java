package com.demo.repositories;

import com.demo.domain.tokenBlacklist.TokenBlacklist;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface TokenBlacklistRepository extends JpaRepository<TokenBlacklist, Long> {

    Boolean existsByToken(String token);
    void deleteByToken(String token);

    @Transactional
    @Modifying
    @Query(value =
            "DELETE FROM Blacklists WHERE token IN :tokens", nativeQuery = true
    )
    void deleteAllByTokenNamesNative(@Param("tokens") List<String> tokens);

    @Transactional
    @Query(value =
            "SELECT t.* FROM Blacklists t " +
            "WHERE t.expire_time <= :dateTime", nativeQuery = true
    )
    List<TokenBlacklist> findByExpireDatenativeCode(@Param(value = "dateTime") LocalDateTime dateTime);
}
