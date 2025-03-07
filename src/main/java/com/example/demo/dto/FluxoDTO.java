package com.example.demo.dto;

import java.util.List;

import com.example.demo.model.Fluxo;

public record FluxoDTO(
    String id,
    String nome,
    List<EtapaDTO> etapas
) {
    public FluxoDTO (Fluxo model) {
        this(
            model.getId(),
            model.getNome(),
            model.getEtapas() == null ? null : model.getEtapas().stream().map(EtapaDTO::parse).toList()
        );
    }

    public static FluxoDTO parse(Fluxo model) {
        return model == null ? null
        : new FluxoDTO(model);
    }
}
