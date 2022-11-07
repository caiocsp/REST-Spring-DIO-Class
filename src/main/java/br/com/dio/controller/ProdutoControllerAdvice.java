package br.com.dio.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import br.com.dio.exception.ProductNullException;
import br.com.dio.exception.ProductPriceException;

@ControllerAdvice
public class ProdutoControllerAdvice extends ResponseEntityExceptionHandler {
//Teremos um controller de erros    
//Garantindo que não salve se houver erros na requisição,
//seja informando de forma mais genérica ou mais específica como os exemplos abaixo

    @ExceptionHandler(ProductNullException.class) //Definindo responsável pela exceção
    public ResponseEntity<Object> capturaErroNull(){
        
        Map<String, Object> body = new HashMap<String, Object>();
        
        body.put("message","Verifique os campos do produto");

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(body);
        
    }

    @ExceptionHandler(ProductPriceException.class) //Definindo responsável pela exceção
    public ResponseEntity<Object> capturaErroPreco(){
        
        Map<String, Object> body = new HashMap<String, Object>();
        
        body.put("message","Verifique o preço do produto");

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(body);
        
    }
}
