package com.example.demo.dto;

import java.util.List;

import com.example.demo.model.Etapa;
import com.example.demo.model.EtapaEnum;


public record EtapaDTO(
    String id,
    Integer ordem,
    String nome,
    EtapaEnum etapaId,
    GrupoDTO grupoResponsavel,
    List<AcaoDTO> acoes
) {
    public static EtapaDTO parse(Etapa model){
        if(model == null) return null;

        return new EtapaDTO(
            model.getId(), 
            model.getOrdem(), 
            model.getNome(), 
            model.getEtapaId(),
            GrupoDTO.parse(model.getGrupoResponsavel()), 
            model.getAcoes().stream().map(AcaoDTO::parse).toList()
        );


    }
}
