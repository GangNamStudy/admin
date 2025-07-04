package com.parking.admin.adapter.in.web;

import com.parking.admin.adapter.exception.InfraException;
import com.parking.admin.adapter.in.web.dto.response.ErrorResponseDTO;
import com.parking.admin.domain.common.exception.BusinessLogicException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(BusinessLogicException.class)
    public ResponseEntity<ErrorResponseDTO> handleBusinessLogicException(BusinessLogicException e){
        return ResponseEntity.badRequest().body(new ErrorResponseDTO(e.getMessage()));
    }

    @ExceptionHandler(InfraException.class)
    public ResponseEntity<ErrorResponseDTO> handleInfraException(){
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ErrorResponseDTO("서버 오류"));
    }
}