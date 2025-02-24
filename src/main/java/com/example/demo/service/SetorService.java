package com.example.demo.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import com.example.demo.model.Orgao;
import com.example.demo.model.Setor;
import com.example.demo.repository.SetorRepository;


@Service
public class SetorService {
    
    @Autowired
    private SetorRepository repository;

    public Setor findOrCreate(Setor setor, Orgao orgao){

        Setor probe = new Setor();
        probe.setGuid(setor.getGuid());

        Optional<Setor> optSetor = repository.findBy(Example.of(probe), query -> query.first());

        if(optSetor.isPresent()) {
            return optSetor.get();
        } else {
            setor.setOrgao(orgao);
            return repository.save(setor);
        }

    }

}
