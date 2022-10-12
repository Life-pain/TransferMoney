package com.example.TransferMoney.service;

import com.example.TransferMoney.exeptions.ErrorInputData;
import com.example.TransferMoney.model.ConfirmRequest;
import com.example.TransferMoney.model.Message;
import com.example.TransferMoney.model.OperationId;
import com.example.TransferMoney.model.TransferEntity;
import com.example.TransferMoney.repository.TransferRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class TransferService {

    private final TransferRepository transferRepository;
    private final String PIN_CODE = "0000";

    private OperationId operationId = new OperationId();

    public TransferService(TransferRepository transferRepository) {
        this.transferRepository = transferRepository;
    }

    public ResponseEntity<OperationId> doTransferMoney(TransferEntity transferEntity) {
        String responseMessage = transferEntityValidation(transferEntity);

        boolean transferStatus = responseMessage.isEmpty();
        transferRepository.logTransferMoney(transferEntity, transferStatus);
        operationId.incrementOperationId();
        if (!transferStatus)
            throw new ErrorInputData(new Message(responseMessage, Integer.parseInt(operationId.getOperationId())));
        return new ResponseEntity<>(operationId, HttpStatus.OK);
    }

    public ResponseEntity<OperationId> doConfirmOperation(ConfirmRequest confirmRequest) {
        if (!confirmRequest.getCode().equals(PIN_CODE)) throw new ErrorInputData(
                new Message("Не верно веден код", Integer.parseInt(confirmRequest.getOperationId())));
        return new ResponseEntity<>(new OperationId(confirmRequest.getOperationId()), HttpStatus.OK);
    }

    public String transferEntityValidation(TransferEntity transferEntity){
        String result = "";
        if (transferEntity.getCardFromNumber().length() != 16)
            result = "Номер карты отправителя должен состоять из 16 знаков";
        if (!transferEntity.getCardFromNumber().matches("[0-9]+"))
            result = "Номер карты отправителя должен состоять только из цифр";
        if (transferEntity.getCardToNumber().length() != 16)
            result = "Номер карты получателя должен состоять из 16 знаков";
        if (!transferEntity.getCardToNumber().matches("[0-9]+"))
            result = "Номер карты получателя должен состоять только из цифр";
        if (transferEntity.getCardFromCVV().length() != 3)
            result = "CVV карты отправителя должен состоять из 3 цифр";
        if (!transferEntity.getCardFromCVV().matches("[0-9]+"))
            result = "CVV карты отправителя должен состоять только из цифр";
        if (transferEntity.getAmount().getValue() < 0)
            result = "Сумма отправления должна быть больше 0";

        return result;
    }
}
