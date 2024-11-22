package com.example.demo.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Ano;
import com.example.demo.repository.AnoRepository;

@Service
public class AnoService {
    
    @Autowired
    private AnoRepository repository;

    public Ano findOrCreate(String ano){

        Optional<Ano> result = repository.findByAno(ano);
        
        if(result.isPresent()) {
            return result.get();
        } else {
            return repository.save(new Ano(ano));
        }

    }

}
