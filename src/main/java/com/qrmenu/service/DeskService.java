package com.qrmenu.service;

import com.qrmenu.data.DeskRepository;
import com.qrmenu.domain.Desk;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class DeskService {

    @Autowired
    DeskRepository deskRepository;

    public Iterable<Desk> allDesks(){return deskRepository.findAll();}

    public ResponseEntity<Message> addDesk(Desk desk){
        deskRepository.save(desk);

        return ResponseEntity.status(HttpStatus.OK)
                .body(new Message("Desk added successfully", desk.getId()));
    }
}
