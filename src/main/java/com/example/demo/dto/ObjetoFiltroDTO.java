package com.example.demo.dto;

import lombok.Getter;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ObjetoFiltroDTO {
    private String exercicio;
    private String nome;
    private String unidadeId;
    private String status;
}
