package com.example.demo.exception;

public class GrupoNaoEncotradoException extends RuntimeException {
    
    public GrupoNaoEncotradoException(String id) {
        super("Grupo com id " + id + " não encontrado");
    }

}
