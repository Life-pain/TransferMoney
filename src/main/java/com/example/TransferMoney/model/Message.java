package com.example.TransferMoney.model;

import lombok.Data;

@Data
public class Message {
    public String message;
    public int id;

    public Message(String message, int id) {
        this.message = message;
        this.id = id;
    }
}
