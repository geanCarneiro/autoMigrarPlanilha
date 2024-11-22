package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Conta;
import com.example.demo.model.DataMock;
import com.example.demo.model.Objeto;
import com.example.demo.repository.ObjetoRepository;

@Service
public class ObjetoService {
    
    @Autowired
    private ObjetoRepository repository;

    public Objeto save(Objeto objeto) {
        return repository.save(objeto);
    }


}
