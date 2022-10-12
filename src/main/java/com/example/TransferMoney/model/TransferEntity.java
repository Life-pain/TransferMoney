package com.example.TransferMoney.model;

import lombok.Data;

@Data
public class TransferEntity {
    private String cardFromNumber;
    private String cardFromValidTill;
    private String cardFromCVV;
    private String cardToNumber;
    private Amount amount;

    @Data
    public class Amount{
        private int value;
        private String currency;
    }
}


