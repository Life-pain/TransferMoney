package com.example.TransferMoney.advice;

import com.example.TransferMoney.exeptions.ErrorInputData;
import com.example.TransferMoney.model.Message;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionHandlerAdvice {

    @ExceptionHandler(ErrorInputData.class)
    public ResponseEntity<Message> eidHandler(ErrorInputData e){
        return new ResponseEntity<>(e.getMsg(), HttpStatus.BAD_REQUEST);
    }
}
