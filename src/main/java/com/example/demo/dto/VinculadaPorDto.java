package com.example.demo.dto;

import com.example.demo.model.VinculadaPor;

public record VinculadaPorDto(
    String id,
    FonteOrcamentariaDTO fonteOrcamentaria,
    double autorizado,
    double dispSemReserva,
    double[] empenhado,
    double[] liquidado,
    double[] pago,
    double orcado

) {
    public static VinculadaPorDto parse(VinculadaPor model) {
        return model == null ? null : 
            new VinculadaPorDto(
                model.getId(), 
                model.getFonteOrcamentaria() == null ? null : new FonteOrcamentariaDTO(model.getFonteOrcamentaria()), 
                model.getAutorizado(), 
                model.getDispSemReserva(), 
                model.getEmpenhado(), 
                model.getLiquidado(), 
                model.getPago(), 
                model.getOrcado()
            );
    }
}
