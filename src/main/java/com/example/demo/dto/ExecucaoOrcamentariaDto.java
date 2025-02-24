package com.example.demo.dto;

import java.util.Set;
import java.util.stream.Collectors;

import com.example.demo.model.ExecucaoOrcamentaria;


public record ExecucaoOrcamentariaDto(
    String id,
    Integer anoExercicio,
    Set<VinculadaPorDto> vinculadaPor
) {
    
    public static ExecucaoOrcamentariaDto parse(ExecucaoOrcamentaria model) {
        return model == null ? null :
            new ExecucaoOrcamentariaDto(
                model.getId(), 
                model.getAnoExercicio(), 
                model.getVinculadaPor() == null ? null : model.getVinculadaPor().stream().map(VinculadaPorDto::parse).collect(Collectors.toSet())
            );
    }

}
