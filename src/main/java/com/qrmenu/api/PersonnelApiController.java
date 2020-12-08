package com.qrmenu.api;

import com.qrmenu.data.PersonnelRepository;
import com.qrmenu.domain.Order;
import com.qrmenu.domain.Personnel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path="/personnel",
        produces="application/json")
@CrossOrigin("*")
public class PersonnelApiController {

    private PersonnelRepository personRepo;

    @Autowired
    public PersonnelApiController(PersonnelRepository repo){this.personRepo = repo;}

    @GetMapping(produces = "application/json")
    public Iterable<Personnel> allPersonnel(){return personRepo.findAll();}

    @PostMapping(consumes = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    public Personnel postPersonnel(@RequestBody Personnel personnel){return  personRepo.save(personnel);}

    @PutMapping(path = "/{personnelId}", consumes = "application/json")
    public Personnel putPersonnel(@RequestBody Personnel personnel){return personRepo.save(personnel);}

    @PatchMapping(path = "/personnelId", consumes = "application/json")
    public Personnel patchPersonnel(@PathVariable("personnelId") Integer personnelId,
                                    @RequestBody Personnel patch){
        Personnel personnel = personRepo.findById(personnelId).get();
        if (patch.getEmail() != null){
            personnel.setEmail(patch.getEmail());
        }
        if (patch.getFirstName() != null){
            personnel.setFirstName(patch.getFirstName());
        }
        if (patch.getLastName() != null){
            personnel.setLastName(patch.getLastName());
        }
        if (patch.getRole() != 0){
            personnel.setRole(patch.getRole());
        }
        if (patch.getUsername() != null){
            personnel.setUsername(patch.getUsername());
        }
        return personRepo.save(personnel);
    }
}
