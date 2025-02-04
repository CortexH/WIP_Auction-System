package com.demo.services;

import com.demo.dto.internal.NewUserHistoryDTO;
import com.demo.repositories.UserHistoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserHistoryService {

    @Autowired
    private UserHistoryRepository historyRepository;

    public void createNewUserHistoryByDataOrListOfData(NewUserHistoryDTO data){

    }
    public void createNewUserHistoryByDataOrListOfData(List<NewUserHistoryDTO> data){

    }
}
