package com.example.demo.exception.service;

import lombok.Getter;

import java.util.List;

@Getter
public class InfoplanServiceException extends RuntimeException {

    private final List<String> errors;

    public InfoplanServiceException(List<String> errors) {
        this.errors = errors;
    }
}
