package com.example.demo.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.model.Apontamento;
import com.example.demo.model.Objeto;
import com.example.demo.repository.ApontamentoRepository;


@Service
public class ApontamentoService {
    
    @Autowired
    private ApontamentoRepository apontamentoRepository;

    public Apontamento save(Apontamento apontamento){

        return apontamentoRepository.save(apontamento);

    } 
    
    public void remover(Apontamento apontamento) {
        // apontamentoRepository.delete(apontamento);
        Optional<Apontamento> optApontamento = apontamentoRepository.findById(apontamento.getId());
        if(optApontamento.isPresent()) {
            apontamento = optApontamento.get();
            apontamento.setActive(false);
            apontamentoRepository.save(apontamento);
        }
    }

    public void mergeObjetoApontamento(Apontamento apontamento, Objeto objeto){
        apontamento = apontamentoRepository.save(apontamento);

        apontamentoRepository.mergeObjetoApontamento(objeto.getId(), apontamento.getId());
    }

}
