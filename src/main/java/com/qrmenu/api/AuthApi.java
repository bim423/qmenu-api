package com.qrmenu.api;

import com.qrmenu.data.PersonnelRepository;
import com.qrmenu.domain.Personnel;
import com.qrmenu.service.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping(path = "/auth")
public class AuthApi {

    @Autowired
    PersonnelRepository repo;

    @PostMapping(consumes = "application/json")
    public ResponseEntity<?> auth(@RequestBody Personnel personnel){

        Optional<Personnel> optional = repo.findByUsername(personnel.getUsername());

        if (!optional.isPresent()){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(new Message("Invalid username.", 0));
        }

        Personnel check = optional.get();

        if (check.getPassword().equals(personnel.getPassword())){
            HashMap<String, Object> responseMap = new HashMap<>();
            responseMap.put("message", "Login Successful");
            responseMap.put("user", check);

            return new ResponseEntity<Object>(responseMap, HttpStatus.OK);

        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(new Message("Invalid password", 0));
        }

    }
}
