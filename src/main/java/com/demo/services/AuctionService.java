package com.demo.services;

import com.demo.domain.auction.Auction;
import com.demo.domain.auction.AuctionStatus;
import com.demo.domain.auction.AuctionVisibility;
import com.demo.domain.auctionHistory.AuctionEventType;
import com.demo.domain.user.User;
import com.demo.domain.user.UserRoles;
import com.demo.dto.input.NewAuctionDTO;
import com.demo.dto.internal.NewAuctionHistoryDTO;
import com.demo.dto.output.AuctionAndHistoryDTO;
import com.demo.dto.output.AuctionCreatedDTO;
import com.demo.dto.output.FindAuctionDTO;
import com.demo.dto.output.GenericSuccessDTO;
import com.demo.repositories.AuctionRepository;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.*;

@Slf4j
@Service
@RequiredArgsConstructor
public class AuctionService {

    private final AuctionRepository auctionRepository;
    private final AuctionHistoryService historyService;
    private final UserService userService;

    public AuctionCreatedDTO createNewAuction(NewAuctionDTO data, HttpServletRequest request){

        User user = userService.findUserByHttpServletRequest(request);

        Auction auction = Auction.builder()
                .creation_date(LocalDateTime.now())
                .description(data.description())
                .title(data.title())
                .start_time(data.start_time())
                .end_time(data.start_time().plusHours(3))
                .status(AuctionStatus.SCHEDULED)
                .starting_price(data.starting_price())
                .owner_id(user)
                .winner_id(null)
                .visibility(data.visibility())
                .build();

        Auction saved_auc = auctionRepository.save(auction);

        NewAuctionHistoryDTO historyDTO = new NewAuctionHistoryDTO(LocalDateTime.now(), auction, null, AuctionEventType.CREATED, null, "Created new auction");

        historyService.createNewHistoryByDataOrListOfData(historyDTO);

        return new AuctionCreatedDTO(LocalDateTime.now(), 200, "Auction created", saved_auc.getAuction_id());

    }


    public List<Auction> getAuctionsByLessTimeThanDateTime(LocalDateTime time){
        //LocalDateTime ltime = (time != null) ? time : LocalDateTime.now();
        return auctionRepository.findAllByLessDateTimeThanParam(
                (time != null) ? time : LocalDateTime.now()
        );
    }

    public void closeAuctionsByListOfUUIDOrSingleUUID(List<UUID> list){
        try{
            auctionRepository.closeAuctionsByUUIDList(list);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void closeAuctionsByListOfUUIDOrSingleUUID(UUID id){
        try{
            List<UUID> list = new ArrayList<>();
            list.add(id);
            auctionRepository.closeAuctionsByUUIDList(list);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public Auction findAuctionById(UUID uuid){
        return auctionRepository.findById(uuid)
                .orElseThrow(() -> new NoSuchElementException("Could not find auctions with specified id"));
    }

    public List<Auction> getAllByIdsList(List<UUID> ids){
        return auctionRepository.findAllById(ids);
    }

    public List<FindAuctionDTO> findAllAuctionsFromUser(HttpServletRequest request) {
        User user = userService.findUserByHttpServletRequest(request);

        List<Auction> allAuctions = auctionRepository.findAuctionByUserNativeQuery(user.getUser_id());

        List<FindAuctionDTO> filteredAucs = new ArrayList<>();

        for (Auction auc : allAuctions) {
            filteredAucs.add(new FindAuctionDTO(
                    auc.getTitle(),
                    auc.getCurrent_price(),
                    auc.getStatus(),
                    user.getUsername(),
                    auc.getVisibility()
            ));
        }
        return filteredAucs;

    }

    public GenericSuccessDTO manuallyCancelAuction(HttpServletRequest request, UUID id){
        User user = userService.findUserByHttpServletRequest(request);
        Auction auc = findAuctionById(id);

        log.info(user.getEmail());

        if(
                user.getRoles() == UserRoles.ADMIN ||
                (user.getEmail().equals(auc.getOwner_id().getEmail()))
        ){

            auctionRepository.manuallyCancelAuction(id);

            historyService.createNewHistoryByDataOrListOfData(new NewAuctionHistoryDTO(
                    LocalDateTime.now(), auc, null, AuctionEventType.CANCELED,
                    null, "Auction canceled."
            ));

            return new GenericSuccessDTO(
                    LocalDateTime.now(), 200, ("Auction '" + auc.getTitle() + "' successfully canceled")
            );

        }
        throw new NoSuchElementException("Auction with specified id not found");
    }

    public FindAuctionDTO findAuctionByIdAndFormatToDTO(HttpServletRequest request, UUID id){
        Auction auc = findAuctionById(id);
        User user = userService.findUserByHttpServletRequest(request);

        if(!auc.getOwner_id().getEmail().equals(user.getEmail())) throw new NoSuchElementException("Auction with specified ID not found");



        return null;
    }

    public FindAuctionDTO findAuction_OrAuctionAndHistory(HttpServletRequest request, UUID id){
        return new FindAuctionDTO("A", new BigDecimal(1), AuctionStatus.SCHEDULED, "AAAA", AuctionVisibility.PUBLIC);
    }

    public AuctionAndHistoryDTO findAuction_OrAuctionAndHistory(HttpServletRequest request, UUID id, Boolean bool){
        return null;
    }

}



