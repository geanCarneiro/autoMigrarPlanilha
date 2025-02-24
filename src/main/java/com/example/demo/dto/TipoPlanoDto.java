package com.example.demo.dto;

import com.example.demo.model.TipoPlano;

public record TipoPlanoDto(
    String id,
    String nome,
    String sigla
) {
    public TipoPlanoDto(TipoPlano model){
        this(
            model.getId(), 
            model.getNome(), 
            model.getSigla()
        );
    }
}
