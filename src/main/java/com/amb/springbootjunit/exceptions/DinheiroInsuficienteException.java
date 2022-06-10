package com.amb.springbootjunit.exceptions;

public class DinheiroInsuficienteException extends  RuntimeException{
    public DinheiroInsuficienteException(String message) {
        super(message);
    }
}
