package com.example.demo.dto;

import com.example.demo.model.AreaTematica;

public record AreaTematicaDto(
    String id,
    String nome
) {

    public AreaTematicaDto(AreaTematica model) {
        this(
            model.getId(),
            model.getNome()
        );
    }
    
}
