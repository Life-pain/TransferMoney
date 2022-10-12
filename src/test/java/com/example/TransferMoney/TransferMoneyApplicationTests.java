package com.example.TransferMoney;

import com.example.TransferMoney.model.OperationId;
import com.example.TransferMoney.model.TransferEntity;
import org.json.JSONObject;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.RequestBody;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

@Testcontainers
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class TransferMoneyApplicationTests {
    @Autowired
    private TestRestTemplate testRestTemplate;
    @Container
    private final GenericContainer<?> transferMoney = new GenericContainer<>("transfer_money:latest")
            .withExposedPorts(5500);

    @Test
    void contextLoads() {
        Integer transferMoneyPort = transferMoney.getMappedPort(5500);

        TransferEntity transferEntity = new TransferEntity();
        transferEntity.setCardFromNumber("1111112111111111");
        transferEntity.setCardFromValidTill("01/23");
        transferEntity.setCardFromCVV("354");
        transferEntity.setCardToNumber("2111112111111111");
        TransferEntity.Amount amount = transferEntity.new Amount();
        amount.setValue(3);
        amount.setCurrency("RUR");
        transferEntity.setAmount(amount);

        HttpEntity<TransferEntity> request = new HttpEntity<>(transferEntity);
        ResponseEntity<OperationId> responseEntity = testRestTemplate.postForEntity(
                "http://localhost:" + transferMoneyPort + "/transfer", request, OperationId.class
        );

        Assertions.assertEquals(responseEntity.getStatusCodeValue(), 200);
    }

}
