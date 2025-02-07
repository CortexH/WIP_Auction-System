package com.demo.services;

import com.demo.domain.auctionAllowedUsers.AuctionAllowedUsers;
import com.demo.dto.output.GenericSuccessDTO;
import com.demo.repositories.AuctionAllowedUsersRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AuctionAllowedUsersService {

    private final AuctionAllowedUsersRepository repository;

    public GenericSuccessDTO addUserToAllowlist(UUID userId, UUID auctionId){
        return null;
    }

    public List<AuctionAllowedUsers> returnAllowedUsersFromAuctionId(){
        return null;
    }

}
