package com.example.demo.dto;

import com.example.demo.dto.acessocidadaoapi.UnidadesACResponseDto;
import com.example.demo.model.UnidadeOrcamentaria;

public record UnidadeOrcamentariaDTO(
        String id,
        String guid,
        String codigo,
        String nome,
        String sigla
    )  {
    
    public UnidadeOrcamentariaDTO(UnidadesACResponseDto unidadeAC){
        this(null, unidadeAC.guid(), "", unidadeAC.nomeFantasia(), unidadeAC.sigla());
    }

    public UnidadeOrcamentariaDTO(UnidadeOrcamentaria unidade) {
        this(unidade.getId(), unidade.getGuid(), unidade.getCodigo(), unidade.getNome(), unidade.getSigla());
    }

}
