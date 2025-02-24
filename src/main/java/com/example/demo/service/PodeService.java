package com.example.demo.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Grupo;
import com.example.demo.model.Pode;
import com.example.demo.repository.GrupoRepository;


@Service
public class PodeService {
    
    @Autowired
    private GrupoRepository grupoRepository;


    public Pode findByGrupoModulo(String moduloId, String grupoId){

        Optional<Grupo> optGrupo = grupoRepository.findByGrupoModulo(moduloId, grupoId);

        if(optGrupo.isPresent()) {
            return new ArrayList<>(optGrupo.get().getPermissoes()).get(0) ;
        } 

        return null;
    }



}
