package com.example.demo.service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import com.example.demo.model.PlanoOrcamentario;
import com.example.demo.repository.PlanoOrcamentarioRepository;


@Service
public class PlanoOrcamentarioService {

    @Autowired
    private PlanoOrcamentarioRepository repository;

    public void saveAll(List<PlanoOrcamentario> planos) {
        repository.saveAll(planos);
    }

    public List<PlanoOrcamentario> getAllSimples() {
        return repository.getAllSimples();
    }

    public String getCodById(String idPlano) {
        return repository.getCodById(idPlano);
    }

    public PlanoOrcamentario findOrCreateByCod(PlanoOrcamentario plano){
        
        PlanoOrcamentario probe = new PlanoOrcamentario();
        probe.setCodigo(plano.getCodigo());
        
        Optional<PlanoOrcamentario> optPlano = repository.findBy( Example.of(probe), query -> query.first());

        return optPlano.orElse(plano);        


    }

}
