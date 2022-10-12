package com.example.TransferMoney.model;

import lombok.Data;

@Data
public class OperationId {
    private static String operationId;

    public OperationId() {
        if (operationId == null) operationId = "0";
    }

    public OperationId(String setOperationId) {
        operationId = setOperationId;
    }

    public void incrementOperationId() {
        operationId = String.valueOf(Integer.parseInt(operationId) + 1);
    }

    public String getOperationId() {
        return operationId;
    }


}
