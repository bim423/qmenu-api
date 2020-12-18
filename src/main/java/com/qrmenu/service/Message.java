package com.qrmenu.service;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class Message {

    private String message;

    private int id;

    public Message(String message, int id) {
        this.message = message;
        this.id=id;
    }
}
