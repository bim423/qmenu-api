package com.qrmenu.service;

import com.qrmenu.data.PersonnelRepository;
import com.qrmenu.domain.Personnel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PersonnelService {

    @Autowired
    PersonnelRepository personnelRepo;

    public Iterable<Personnel> getPersonnel(){
        return personnelRepo.findAll();
    }

    public ResponseEntity<Message> addPersonnel(Personnel personnel){
        personnelRepo.save(personnel);
        return ResponseEntity.status(HttpStatus.OK)
                .body(new Message("Personnel is added successfully", personnel.getId()));
    }


    public ResponseEntity<Message> updatePersonnel(Personnel personnel) {

        Optional<Personnel> optional = personnelRepo.findById(personnel.getId());

        if (!optional.isPresent()){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new Message("The personnel id is not valid", personnel.getId()));
        }

        Personnel update = optional.get();

        if (personnel.getUsername() != null){
            update.setUsername(personnel.getUsername());
        }
        if (personnel.getPassword() != null){
            update.setPassword(personnel.getPassword());
        }
        if (personnel.getFirstName() != null){
            update.setFirstName(personnel.getFirstName());
        }
        if (personnel.getLastName() != null){
            update.setLastName(personnel.getLastName());
        }
        if (personnel.getEmail() != null){
            update.setEmail(personnel.getEmail());
        }
        if (personnel.getAdmin() == 0 || personnel.getAdmin() == 1){
            update.setAdmin(personnel.getAdmin());
        }

        personnelRepo.save(update);

        return ResponseEntity.status(HttpStatus.OK)
                .body(new Message("The personnel is updated successfully", personnel.getId()));
    }

    public ResponseEntity<Message> deletePersonnel(Integer pid) {
        try {
            personnelRepo.deleteById(pid);
        }catch (EmptyResultDataAccessException e){

        }
        return ResponseEntity.status(HttpStatus.OK)
                .body(new Message("The personnel deleted successfully", pid));
    }
}
