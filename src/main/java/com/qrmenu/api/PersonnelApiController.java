package com.qrmenu.api;

import com.qrmenu.data.PersonnelRepository;
import com.qrmenu.domain.Personnel;
import com.qrmenu.service.Message;
import com.qrmenu.service.PersonnelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path="/personnel")
@CrossOrigin("*")
public class PersonnelApiController {

    @Autowired
    private PersonnelService personnelService;

    @GetMapping(produces = "application/json")
    public Iterable<Personnel> allPersonnel(){return personnelService.getPersonnel();}

    @PutMapping(consumes = "application/json", path = "/create")
    public ResponseEntity<Message> addPersonnel(@RequestBody Personnel personnel){
        System.out.println(personnel.getId());
        System.out.println(personnel.getUsername());
        return personnelService.addPersonnel(personnel);
    }

    @PutMapping(consumes = "application/json", path = "/update")
    public ResponseEntity<Message> updatePersonnel(@RequestBody Personnel personnel){
        return personnelService.updatePersonnel(personnel);
    }

    @DeleteMapping(path = "/delete/{id}")
    public ResponseEntity<Message> deletePersonnel(@PathVariable("id") Integer pid){
        return personnelService.deletePersonnel(pid);

    }


}
