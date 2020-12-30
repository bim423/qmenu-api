package com.qrmenu.service;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class DeskMessage extends Message{

    private String deskCode;

    public DeskMessage(String message, int id, String deskCode){
        super(message, id);
        this.deskCode = deskCode;
    }




}
