package com.example.demo.dto;

import java.util.Arrays;

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
public class ObjetoTiraDTO {
    
    private String id;
    private String unidadeResponsavel;
    private String codPlano;
    private String nome;
    private String tipo;
    private Double totalPrevisto;
    private Double totalHomologado;
    private Double totalOrcado;
    private Double totalAutorizado;
    private Double totalEmpenhado;
    private Double totalDisponivel;
    private String status;

    public ObjetoTiraDTO(Objeto objeto) {

        this.id = objeto.getId();
        this.nome = objeto.getNome();
        this.tipo = objeto.getTipo();

        UnidadeOrcamentaria unidadeOrcamentaria = objeto.getConta().getUnidadeOrcamentariaImplementadora();
        this.unidadeResponsavel = unidadeOrcamentaria.getCodigo() + " - " + unidadeOrcamentaria.getSigla();
        this.codPlano = objeto.getConta().getPlanoOrcamentario() == null ? "Sem P.O." : objeto.getConta().getPlanoOrcamentario().getCodigo();

        this.totalPrevisto = 0d;
        this.totalHomologado = 0d;
        this.status = objeto.getEmStatus() == null ? "null" : objeto.getEmStatus().getStatus().getNome();

        objeto.getCustosEstimadores().forEach(custo -> {

            custo.getIndicadaPor().forEach(indicadaPor -> {

                this.totalPrevisto += indicadaPor.getPrevisto();
                this.totalHomologado += indicadaPor.getContratado();
            });

        });
        
        this.totalOrcado = 0d;
        this.totalAutorizado = 0d;
        this.totalDisponivel = 0d;
        this.totalEmpenhado = 0d;

        objeto.getConta().getExecucoesOrcamentaria().forEach(exec -> {

            exec.getVinculadaPor().forEach(vinculadaPor -> {
                this.totalOrcado += vinculadaPor.getOrcado();
                this.totalAutorizado += vinculadaPor.getAutorizado();
                this.totalDisponivel += vinculadaPor.getDispSemReserva();
                this.totalEmpenhado += Arrays.stream(vinculadaPor.getEmpenhado()).reduce(0, Double::sum); 
            });

            
        });
    }
}
