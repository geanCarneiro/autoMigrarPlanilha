package com.example.demo.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Investimento;
import com.example.demo.model.PlanoOrcamentario;
import com.example.demo.model.UnidadeOrcamentaria;
import com.example.demo.repository.InvestimentoRepository;

@Service
public class InvestimentoService {
    
    @Autowired
    private InvestimentoRepository repository;

    public Investimento findOrCreate(String nome, UnidadeOrcamentaria unidade, PlanoOrcamentario plano){
        Optional<Investimento> result = repository.findByFilter(unidade, plano);

        if(result.isPresent()) {
            return result.get();
        } else {
            return repository.save(new Investimento(nome, unidade, plano));
        }
    }

}
