package com.example.TransferMoney.Controller;

import com.example.TransferMoney.model.ConfirmRequest;
import com.example.TransferMoney.model.OperationId;
import com.example.TransferMoney.model.TransferEntity;
import com.example.TransferMoney.service.TransferService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "https://serp-ya.github.io")
public class Controller {
    private final TransferService service;

    public Controller(TransferService service) {
        this.service = service;
    }

    @PostMapping("/transfer")
    public ResponseEntity<OperationId> test(@RequestBody TransferEntity transferEntity) {
        return service.doTransferMoney(transferEntity);
    }

    @PostMapping("/confirmOperation")
    public ResponseEntity<OperationId> confirmOperation(@RequestBody ConfirmRequest confirmRequest) {
        return service.doConfirmOperation(confirmRequest);
    }
}
