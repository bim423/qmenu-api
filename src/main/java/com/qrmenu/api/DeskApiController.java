package com.qrmenu.api;

import com.qrmenu.domain.Desk;
import com.qrmenu.service.DeskMessage;
import com.qrmenu.service.DeskService;
import com.qrmenu.service.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.NoSuchAlgorithmException;

@RestController
@RequestMapping(path = "/desk")
public class DeskApiController {

    @Autowired
    DeskService deskService;

    @GetMapping(produces = "application/json")
    public Iterable<Desk> allDesks(){
        return deskService.allDesks();
    }

    @PutMapping(consumes = "application/json")
    public ResponseEntity<Message> putDesk(@RequestBody Desk desk){
        return deskService.addDesk(desk);
    }

    @PostMapping(path = "/update/{id]")
    public ResponseEntity<DeskMessage> updateDeskCode(@PathVariable("id") Integer id)
            throws NoSuchAlgorithmException {
            return deskService.updateDeskCode(id);
    }

    @GetMapping(path = "/{code}")
    public Desk getDeskByCode(@PathVariable("code") String code){
        return deskService.getDeskByCode(code);
    }


}
