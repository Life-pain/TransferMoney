package com.example.TransferMoney.exeptions;

import com.example.TransferMoney.model.Message;

public class ErrorInputData extends RuntimeException{
    private Message msg;
    public ErrorInputData(Message msg) {
        super();
        this.msg = msg;
    }

    public Message getMsg() {
        return msg;
    }
}
