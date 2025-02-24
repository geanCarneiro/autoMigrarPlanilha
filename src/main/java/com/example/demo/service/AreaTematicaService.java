package com.example.demo.service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import com.example.demo.model.AreaTematica;
import com.example.demo.repository.AreaTematicaRepository;


@Service
public class AreaTematicaService {

    @Autowired
    private AreaTematicaRepository repository;

    public List<AreaTematica> findAll() {

        return repository.findAll();
    }

    public AreaTematica findOrCreateByNome(AreaTematica areaTematica) {

        AreaTematica areaProbe = new AreaTematica( 
            areaTematica.getNome()
        );

        return repository.findBy(Example.of(areaProbe), q -> q.first()).orElse(areaTematica);
        

    }
    
}
