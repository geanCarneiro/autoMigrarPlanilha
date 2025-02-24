package com.example.demo.dto;

import lombok.Getter;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class InvestimentoFiltroDTO {
    private String nome;
    private String codUnidade;
    private String codPO;
    private String idFonte;
    private String exercicio;
}
