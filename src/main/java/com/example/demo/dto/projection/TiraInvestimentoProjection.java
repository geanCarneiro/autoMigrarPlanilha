package com.example.demo.dto.projection;

public record TiraInvestimentoProjection(
    String id,
    String codPO,
    String unidadeOrcamentaria,
    Double totalPrevisto,
    Double totalContratado,
    Double totalOrcado,
    Double totalAutorizado,
    Double totalEmpenhado,
    Double totalDisponivel
) {
    
}
