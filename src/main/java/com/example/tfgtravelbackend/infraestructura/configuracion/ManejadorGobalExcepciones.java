package com.example.tfgtravelbackend.infraestructura.configuracion;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.bind.MethodArgumentNotValidException;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class ManejadorGobalExcepciones {

    //los errores de RuntimeExcepcion
    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<Map<String, String>> manejarRuntimeException(RuntimeException ex) {
        Map<String, String> error = new HashMap<>();
        error.put("error", ex.getMessage());
        error.put("estado", "ERROR");
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    //captura errores de validacion
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> manejarValidacion(MethodArgumentNotValidException ex) {
        Map<String, String> errores = new HashMap<>();

        ex.getBindingResult().getFieldErrors().forEach(error -> {
            errores.put(error.getField(), error.getDefaultMessage());
        });

        return new ResponseEntity<>(errores, HttpStatus.BAD_REQUEST);
    }

    //errores de validacion
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<Map<String, String>> manejarArgumentoInvalido(IllegalArgumentException ex) {
        Map<String, String> error = new HashMap<>();
        error.put("error", ex.getMessage());
        error.put("estado", "ERROR_VALIDACION");
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    //cualquier error no controlado
    @ExceptionHandler(Exception.class)
    public ResponseEntity<Map<String, String>> manejarException(Exception ex) {
        Map<String, String> error = new HashMap<>();
        error.put("error", "Error interno del servidor");
        error.put("detalle", ex.getMessage());
        error.put("estado", "ERROR_INTERNO");
        return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
