package com.example.demo.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.demo.model.Ano;
import com.example.demo.model.Conta;
import com.example.demo.model.ExecucaoOrcamentaria;
import com.example.demo.model.FonteOrcamentaria;
import com.example.demo.repository.ExecucaoOrcamentariaRepository;

@Service
public class ExecucaoOrcamentariaService {
    
    private ExecucaoOrcamentariaRepository repository = new ExecucaoOrcamentariaRepository();

    public ExecucaoOrcamentaria findOrCreate(Ano ano, Conta conta, FonteOrcamentaria fonte){
        
        Optional<ExecucaoOrcamentaria> result = repository.findByFilter(ano, conta, fonte);

        if(result.isPresent()) {
            return result.get();
        } else {
            return repository.save(new ExecucaoOrcamentaria(ano, conta, fonte));
        }
        
    }

    public ExecucaoOrcamentaria setValores(ExecucaoOrcamentaria execucaoOrcamentaria){
        ExecucaoOrcamentaria execucao = findOrCreate(execucaoOrcamentaria.getAnoExercicio(), execucaoOrcamentaria.getContaDelimitada(), execucaoOrcamentaria.getFonteOrcamentariaVinculadora());

        execucao.setOrcamento(execucaoOrcamentaria.getOrcamento());
        execucao.setAutorizado(execucaoOrcamentaria.getAutorizado());

        return repository.save(execucao);
    }

}
