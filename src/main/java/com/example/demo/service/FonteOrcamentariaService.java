package com.example.demo.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.FonteOrcamentaria;
import com.example.demo.repository.FonteOrcamentariaRepository;

@Service
public class FonteOrcamentariaService {
    
    @Autowired
    private FonteOrcamentariaRepository repository;

    public FonteOrcamentaria findOrCreate(Long codigo, String nome){
        
        Optional<FonteOrcamentaria> result = repository.findByCodigo(codigo);

        if(result.isPresent()){
            return result.get();
        } else {
            return repository.save(new FonteOrcamentaria(codigo, nome));
        }
    }

}
