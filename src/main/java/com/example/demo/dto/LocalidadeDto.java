package com.example.demo.dto;

import com.example.demo.model.Localidade;

public record LocalidadeDto(
    String id,
    String nome
) {
    
    public LocalidadeDto(Localidade model){
        this(model.getId(), model.getNome());
    }

}
