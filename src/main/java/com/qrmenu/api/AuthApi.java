package com.qrmenu.api;

import com.qrmenu.data.PersonnelRepository;
import com.qrmenu.domain.Personnel;
import com.qrmenu.service.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping(path = "/auth")
public class AuthApi {

    @Autowired
    PersonnelRepository repo;

    @PutMapping(consumes = "application/json")
    public ResponseEntity<Message> auth(@RequestBody Personnel personnel){

        Optional<Personnel> optional = repo.findByUsername(personnel.getUsername());

        if (!optional.isPresent()){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new Message("Username not found", 0));
        }

        Personnel check = optional.get();

        if (check.getPassword().equals(personnel.getPassword())){
            return ResponseEntity.status(HttpStatus.OK)
                    .body(new Message("Login successful" , 0));
        }else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(new Message("Invalid password", 0));
        }


    }
}
