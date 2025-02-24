package com.example.demo.service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.example.demo.model.TipoPlano;
import com.example.demo.repository.TipoPlanoRepository;


@Service
public class TipoPlanoService {
    
    @Autowired
    private TipoPlanoRepository repository;


    public List<TipoPlano> findAll(){
        
        return repository.findAll(Sort.by(Sort.Direction.ASC, "nome"));
    }

    public Optional<TipoPlano> findById(String id) {
        return repository.findById(id);
    }

    public Optional<TipoPlano> findBySigla(String sigla) {

        TipoPlano probe = new TipoPlano();
        probe.setSigla(sigla);

        return repository.findBy(Example.of(probe), q -> q.first());

    }
    

}
