package com.example.TransferMoney.repository;

import com.example.TransferMoney.model.TransferEntity;
import org.springframework.stereotype.Repository;

import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Repository
public class TransferRepository {
    private SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");

    public void logTransferMoney(TransferEntity transferEntity, boolean transferStatus) {
        try (FileWriter writer = new FileWriter("log.txt", true)) {
            writer.write(String.format(
                    "%s  - Перевод с карты: %s на карту: %s\n" +
                            "Сумма: %d %s Комиссия: %.2f Статус: %s\n\n",
                    formatter.format(new Date(System.currentTimeMillis())),
                    transferEntity.getCardFromNumber(),
                    transferEntity.getCardToNumber(),
                    transferEntity.getAmount().getValue() / 100,
                    transferEntity.getAmount().getCurrency(),
                    (double) transferEntity.getAmount().getValue() / 10000,
                    transferStatus ? "Выполнен" : "Не выполнен"
            ));
            writer.flush();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
