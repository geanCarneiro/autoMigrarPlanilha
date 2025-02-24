package com.example.demo.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import com.example.demo.model.Orgao;
import com.example.demo.repository.OrgaoRepository;


@Service
public class OrgaoService {
    
    @Autowired
    private OrgaoRepository repository;

    public Orgao findOrCreate(Orgao orgao){

        Orgao probe = new Orgao();
        probe.setGuid(orgao.getGuid());

        Optional<Orgao> optOrgao = repository.findBy(Example.of(probe), query -> query.first());

        if(optOrgao.isPresent()) {
            return optOrgao.get();
        } else {
            return repository.save(orgao);
        }

    }

}
