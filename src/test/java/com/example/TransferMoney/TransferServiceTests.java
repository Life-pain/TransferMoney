package com.example.TransferMoney;

import com.example.TransferMoney.exeptions.ErrorInputData;
import com.example.TransferMoney.model.TransferEntity;
import com.example.TransferMoney.repository.TransferRepository;
import com.example.TransferMoney.service.TransferService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;

@ExtendWith(MockitoExtension.class)
public class TransferServiceTests {
    @InjectMocks
    private TransferService transferService;
    @Mock
    private TransferRepository transferRepository;
    private static TransferEntity transferEntity = new TransferEntity();

    @BeforeAll
    public static void init() {
        transferEntity.setCardFromNumber("1111111111111111");
        transferEntity.setCardToNumber("2222222222222222");
        transferEntity.setCardFromCVV("123");
        TransferEntity.Amount amount = transferEntity.new Amount();
        amount.setValue(1);
        transferEntity.setAmount(amount);
    }

    @Test
    public void testHttpStatusDoTransferMoney() {
        ResponseEntity responseEntity = new ResponseEntity(HttpStatus.OK);
        Assertions.assertEquals(responseEntity.getStatusCodeValue(),
                transferService.doTransferMoney(transferEntity).getStatusCodeValue());
    }

    @Test
    public void testThrowDoTransferMoney() {
        transferEntity.setCardToNumber("1");
        Assertions.assertThrows(ErrorInputData.class, () -> transferService.doTransferMoney(transferEntity));
    }

    @Test
    public void testTransferEntityValidation() {
        ArrayList<String> expectedList = new ArrayList<>() {
            {
                add("");
                add("Номер карты отправителя должен состоять из 16 знаков");
                add("Номер карты отправителя должен состоять только из цифр");
                add("Номер карты получателя должен состоять из 16 знаков");
                add("Номер карты получателя должен состоять только из цифр");
                add("CVV карты отправителя должен состоять из 3 цифр");
                add("CVV карты отправителя должен состоять только из цифр");
                add("Сумма отправления должна быть больше 0");
            }
        };

        ArrayList<String> resultsList = new ArrayList<>();
        resultsList.add(transferService.transferEntityValidation(transferEntity));
        transferEntity.setCardFromNumber("1");
        resultsList.add(transferService.transferEntityValidation(transferEntity));
        transferEntity.setCardFromNumber("a");
        resultsList.add(transferService.transferEntityValidation(transferEntity));
        transferEntity.setCardToNumber("2");
        resultsList.add(transferService.transferEntityValidation(transferEntity));
        transferEntity.setCardToNumber("b");
        resultsList.add(transferService.transferEntityValidation(transferEntity));
        transferEntity.setCardFromCVV("3");
        resultsList.add(transferService.transferEntityValidation(transferEntity));
        transferEntity.setCardFromCVV("c");
        resultsList.add(transferService.transferEntityValidation(transferEntity));
        transferEntity.getAmount().setValue(-1);
        resultsList.add(transferService.transferEntityValidation(transferEntity));
        Assertions.assertLinesMatch(expectedList, resultsList);
    }

}
