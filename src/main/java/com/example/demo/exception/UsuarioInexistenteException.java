package com.example.demo.exception;

public class UsuarioInexistenteException extends RuntimeException {
    
    public UsuarioInexistenteException(){
        super("Usuario inexistente");
    }

}
