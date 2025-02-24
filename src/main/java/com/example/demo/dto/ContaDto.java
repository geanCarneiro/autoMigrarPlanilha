package com.example.demo.dto;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.example.demo.model.Conta;



public record ContaDto(
    String id,
    PlanoOrcamentarioDTO planoOrcamentario,
    UnidadeOrcamentariaDTO unidadeOrcamentariaImplementadora,
    List<ExecucaoOrcamentariaDto> execucoesOrcamentaria
) {
    public ContaDto(Conta model) {
        this(
            model.getId(), 
            model.getPlanoOrcamentario() == null ? null : new PlanoOrcamentarioDTO(model.getPlanoOrcamentario()), 
            model.getUnidadeOrcamentariaImplementadora() == null ? null : new UnidadeOrcamentariaDTO(model.getUnidadeOrcamentariaImplementadora()), 
            model.getExecucoesOrcamentaria() == null ? null : model.getExecucoesOrcamentaria().stream().map(ExecucaoOrcamentariaDto::parse).toList()
        );
    }


}
