package com.example.demo.service;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import com.example.demo.model.Localidade;
import com.example.demo.repository.LocalidadeRepository;


@Service
public class LocalidadeService {
    
    @Autowired
    private LocalidadeRepository repository;


    public List<Localidade> findAll() {
   
        return repository.findAll();
    }

    public Localidade findOrCreateByNome(Localidade localidade){

        Localidade locProbe = new Localidade(localidade.getNome());

        return repository.findBy(Example.of(locProbe), q -> q.first()).orElse(localidade);

    }

}
