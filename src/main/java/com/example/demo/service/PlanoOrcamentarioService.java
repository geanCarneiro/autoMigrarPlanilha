package com.example.demo.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.PlanoOrcamentario;
import com.example.demo.repository.PlanoOrcamentarioRepository;

@Service
public class PlanoOrcamentarioService {
    
    @Autowired
    private PlanoOrcamentarioRepository repository;

    public PlanoOrcamentario findOrCreate(Long codPlano, String nomePlano) {
        
        Optional<PlanoOrcamentario> result = repository.findByCodigo(codPlano);

        if (result.isPresent()) {
            return result.get();
        } else {
            return repository.save(new PlanoOrcamentario(codPlano, nomePlano));
        }


    }

}
