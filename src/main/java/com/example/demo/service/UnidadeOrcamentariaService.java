package com.example.demo.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.PlanoOrcamentario;
import com.example.demo.model.UnidadeOrcamentaria;
import com.example.demo.repository.UnidadeOrcamentariaRepository;

@Service
public class UnidadeOrcamentariaService {
    
    @Autowired
    private UnidadeOrcamentariaRepository repository;

    public UnidadeOrcamentaria findOrCreate(Long codigo, String sigla, PlanoOrcamentario plano){
        
        Optional<UnidadeOrcamentaria> result = repository.findByCodigo(codigo);
        UnidadeOrcamentaria unidade;

        if(result.isPresent()) {
            unidade = result.get();
        } else {
            unidade = new UnidadeOrcamentaria(codigo, sigla);
        }

        unidade.addPlanoSeNaoExistir(plano);

        return repository.save(unidade);
    }

}
