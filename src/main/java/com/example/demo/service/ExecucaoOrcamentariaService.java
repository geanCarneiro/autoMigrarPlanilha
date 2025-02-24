package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.ExecucaoOrcamentaria;
import com.example.demo.repository.ExecucaoOrcamentariaRepository;


@Service
public class ExecucaoOrcamentariaService {
    
    @Autowired
    private ExecucaoOrcamentariaRepository repository;

    public ExecucaoOrcamentaria save(ExecucaoOrcamentaria execucao) {
        return repository.save(execucao);
    }

    public void saveAll(List<ExecucaoOrcamentaria> execucoesOrcamentarias) {
        repository.saveAll(execucoesOrcamentarias);
    }

    public Double getTotalOrcadoByAno(String ano) {
        return repository.getTotalOrcadoByAno(ano);
    }


}

