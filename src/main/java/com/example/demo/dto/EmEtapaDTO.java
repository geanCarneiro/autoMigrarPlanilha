package com.example.demo.dto;

import com.example.demo.model.EmEtapa;

public record EmEtapaDTO(
    String id,
    EtapaDTO etapa,
    String atividade,
    boolean devolvido
) {
    public static EmEtapaDTO parse(EmEtapa model) {
        return model == null ? null
        : new EmEtapaDTO(
            model.getId(), 
            EtapaDTO.parse(model.getEtapa()), 
            model.getAtividade(),
            model.isDevolvido()
        );
    }
}
