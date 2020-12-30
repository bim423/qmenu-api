package com.qrmenu.service;

public class DeskMessage extends Message{

    private String deskCode;

    public DeskMessage(String message, int id, String deskCode){
        super(message, id);
        this.deskCode = deskCode;
    }
}
