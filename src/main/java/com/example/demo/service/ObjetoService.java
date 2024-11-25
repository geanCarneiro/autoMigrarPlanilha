package com.example.demo.service;

import org.springframework.stereotype.Service;

import com.example.demo.model.Objeto;
import com.example.demo.repository.ObjetoRepository;

@Service
public class ObjetoService {
    
    private ObjetoRepository repository = new ObjetoRepository();

    public Objeto save(Objeto objeto) {
        return repository.save(objeto);
    }


}
