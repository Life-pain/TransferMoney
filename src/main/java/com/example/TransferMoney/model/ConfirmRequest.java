package com.example.TransferMoney.model;


import lombok.Data;

@Data
public class ConfirmRequest {
    private String operationId;
    private String code;

    public ConfirmRequest(String operationId, String code) {
        this.operationId = operationId;
        this.code = code;
    }
}