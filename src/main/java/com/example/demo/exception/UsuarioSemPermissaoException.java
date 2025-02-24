package com.example.demo.exception;

public class UsuarioSemPermissaoException extends RuntimeException {

    public UsuarioSemPermissaoException() {
        super("Usuário sem permissão.");
    }
}
