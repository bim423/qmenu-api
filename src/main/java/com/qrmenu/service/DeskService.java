package com.qrmenu.service;

import com.qrmenu.data.DeskRepository;
import com.qrmenu.domain.Desk;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.security.NoSuchAlgorithmException;
import java.util.Optional;
import java.util.Spliterator;

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

    public ResponseEntity<DeskMessage> updateDeskCode(Integer id) throws NoSuchAlgorithmException {
        Optional<Desk> optionalDesk = deskRepository.findById(id);

        if (!optionalDesk.isPresent()){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new DeskMessage("Desk id is not valid", id, ""));
        }

        Desk desk = optionalDesk.get();

        desk.deskCode();
        deskRepository.save(desk);

        return ResponseEntity.status(HttpStatus.OK)
                .body(new DeskMessage("Desk code is updated", desk.getId(), desk.getCode()));

    }

    public Desk getDeskByCode(String code){
        Optional<Desk> optionalDesk = deskRepository.findDeskByCode(code);

        if (!optionalDesk.isPresent()){
            return null;
        }

        return optionalDesk.get();
    }

    public ResponseEntity<Message> deleteDeskById(Integer id){
        Optional<Desk> optional = deskRepository.findById(id);

        if (!optional.isPresent()){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new Message("Desk ID is not valid", id));
        }

        try {
            deskRepository.deleteById(id);
        }catch (EmptyResultDataAccessException e){

        }

        return ResponseEntity.status(HttpStatus.OK)
                .body(new Message("Desk deleted successfully", id));


    }

    public Desk findById(Integer id) {

        Optional<Desk> optional = deskRepository.findById(id);

        if (!optional.isPresent()){
            return null;
        }

        return optional.get();
    }
}
