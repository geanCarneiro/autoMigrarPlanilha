package com.example.demo.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.demo.model.FonteOrcamentaria;
import com.example.demo.repository.FonteOrcamentariaRepository;

@Service
public class FonteOrcamentariaService {
    
    private FonteOrcamentariaRepository repository = new FonteOrcamentariaRepository();

    public FonteOrcamentaria findOrCreate(Long codigo, String nome){
        
        Optional<FonteOrcamentaria> result = repository.findByCodigo(codigo);

        if(result.isPresent()){
            return result.get();
        } else {
            return repository.save(new FonteOrcamentaria(codigo, nome));
        }
    }

}
