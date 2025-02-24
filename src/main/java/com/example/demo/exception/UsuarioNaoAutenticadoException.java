package com.example.demo.exception;

public class UsuarioNaoAutenticadoException extends RuntimeException {
    
    public UsuarioNaoAutenticadoException() {
        super("Usuario não autenticado, favor fazer o login!");
    }
}
