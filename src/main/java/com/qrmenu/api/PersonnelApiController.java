package com.qrmenu.api;

import com.qrmenu.data.PersonnelRepository;
import com.qrmenu.domain.Personnel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path="/personnel",
        produces="application/json")
@CrossOrigin("*")
public class PersonnelApiController {

    private PersonnelRepository repo;

    @Autowired
    public PersonnelApiController(PersonnelRepository repo){this.repo = repo;}

    @GetMapping(produces = "application/json")
    public Iterable<Personnel> allPersonnel(){return repo.findAll();}

    @PostMapping(consumes = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    public Personnel postPersonnel(@RequestBody Personnel personnel){return  repo.save(personnel);}

    @PutMapping(path = "/{personnelId}", consumes = "application/json")
    public Personnel putPersonnel(@RequestBody Personnel personnel){return repo.save(personnel);}

    @PatchMapping(path = "/personnelId", consumes = "application/json")
    public Personnel patchPersonnel(@PathVariable("personnelId") Integer personnelId,
                                    @RequestBody Personnel patch){
        Personnel personnel = repo.findById(personnelId).get();
        if (patch.getEmail() != null){
            personnel.setEmail(patch.getEmail());
        }
        if (patch.getFirstName() != null){
            personnel.setFirstName(patch.getFirstName());
        }
        if (patch.getLastName() != null){
            personnel.setLastName(patch.getLastName());
        }
        if (patch.getAdmin() != 0){
            personnel.setAdmin(patch.getAdmin());
        }
        if (patch.getUsername() != null){
            personnel.setUsername(patch.getUsername());
        }
        return repo.save(personnel);
    }
}
