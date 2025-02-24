package com.example.demo.dto;

import com.example.demo.model.IndicadaPor;

public record IndicadaPorDto(
    String id,
    FonteOrcamentariaDTO fonteOrcamentaria,
    Double previsto,
    Double contratado
) {

    public IndicadaPorDto(IndicadaPor model) {
        this(
            model.getId(),
            new FonteOrcamentariaDTO(model.getFonteOrcamentaria()), 
            model.getPrevisto(), 
            model.getContratado()
        );
        
    }
} 