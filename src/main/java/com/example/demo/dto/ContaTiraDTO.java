package com.example.demo.dto;

import java.util.List;
import java.util.stream.Collectors;

import com.example.demo.model.Conta;
import com.example.demo.model.Objeto;
import com.example.demo.model.UnidadeOrcamentaria;

import lombok.Getter;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ContaTiraDTO {

    private String codPO;
    private String unidadeOrcamentaria;
    private Double totalPrevisto;
    private Double totalHomologado;
    private Double totalOrcado;
    private Double totalAutorizado;
    private Double totalDisponivel;
    

    private List<ObjetoTiraDTO> objetos;
    
    public ContaTiraDTO(Conta conta, List<Objeto> objetos){
        
        this.codPO = conta.getPlanoOrcamentario().getCodigo();
        UnidadeOrcamentaria unidadeOrcamentaria = conta.getUnidadeOrcamentariaImplementadora();
        this.unidadeOrcamentaria = unidadeOrcamentaria.getCodigo() + " - " + unidadeOrcamentaria.getSigla();

        this.objetos = objetos.stream().map(objeto -> {
            return new ObjetoTiraDTO(objeto);
        }).collect(Collectors.toList());

        this.totalPrevisto = 0d;
        this.totalHomologado = 0d;
        this.totalOrcado = 0d;
        this.totalAutorizado = 0d;
        this.totalDisponivel = 0d;

        this.objetos.forEach(obj -> {
            this.totalPrevisto += obj.getTotalPrevisto();
            this.totalHomologado += obj.getTotalHomologado();
            this.totalOrcado += obj.getTotalOrcado();
            this.totalAutorizado += obj.getTotalAutorizado();
            this.totalDisponivel += obj.getTotalDisponivel();
        });

    }

    
}