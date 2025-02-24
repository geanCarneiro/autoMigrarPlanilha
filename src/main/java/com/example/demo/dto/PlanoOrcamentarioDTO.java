package com.example.demo.dto;

import com.example.demo.model.PlanoOrcamentario;

public record PlanoOrcamentarioDTO(
    String id, 
    String codigo, 
    String nome
)  {
    
    public PlanoOrcamentarioDTO(PlanoOrcamentario model) {
        this(model.getId(), model.getCodigo(), model.getNome());
    }
       

}
