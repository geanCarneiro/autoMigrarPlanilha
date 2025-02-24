package com.example.demo.model;

import java.util.List;

import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Relationship;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Node
public class Acao extends Entidade {
    


    private String nome;
    private String atividadeFinal;
    private AcaoEnum acaoId;
    private Boolean positivo;

    @Relationship("GERA")
    private Status statusFinal;

    @Relationship("VAI_PARA")
    private Etapa proxEtapa;

}
