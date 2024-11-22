package com.example.demo.model;

import org.springframework.data.neo4j.core.schema.Node;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Node
public class Funcao extends Entidade {
    
    private String nome;

    public Funcao(String nome){
        this.nome = nome;
    }

}
