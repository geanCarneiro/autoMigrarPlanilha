package com.example.demo.dto;

import com.example.demo.model.Pode;

public record PodeDto(
    String id,
    ModuloDto modulo,
    boolean listar,
    boolean visualizar,
    boolean criar,
    boolean editar, 
    boolean excluir,
    boolean verTodasUnidades
) {
    
    public PodeDto (Pode model) {
        this(
            model.getId(), 
            new ModuloDto(model.getModulo()), 
            model.isListar(), 
            model.isVisualizar(), 
            model.isCriar(), 
            model.isEditar(), 
            model.isExcluir(),
            model.isVerTodasUnidades()
        );
    }

}
