package com.example.demo.model;

import java.util.List;

import org.springframework.data.neo4j.core.schema.Node;

import lombok.NoArgsConstructor;

@NoArgsConstructor
@Node
public class Investimento extends Conta{
    
    public Investimento(PlanoOrcamentario po, UnidadeOrcamentaria uo, List<ExecucaoOrcamentaria> execs){
        super(po, uo, execs);
    }

}
