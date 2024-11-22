package com.example.demo.model;

import org.springframework.data.neo4j.core.schema.Node;

import lombok.NoArgsConstructor;

@NoArgsConstructor
@Node
public class Investimento extends Conta{
    
    public Investimento(String nome) {
        super.setNome(nome);
    }

    public Investimento(String nome, UnidadeOrcamentaria unidade, PlanoOrcamentario plano) {
        super.setNome(nome);
        super.setUnidadeOrcamentariaImplementadora(unidade);
        super.setPlanoOrcamentarioOrientador(plano);
    }

    public static Investimento criar(String nome){
        Investimento novo = new Investimento(nome);
        DataMock.noInvestimentos.add(novo);
        return novo;
    }

}
