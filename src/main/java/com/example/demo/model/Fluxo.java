package com.example.demo.model;

import java.util.List;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Relationship;

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
public class Fluxo extends Entidade {
    
    private String nome;
    private String fluxoId;

    @Relationship("POSSUI")
    private List<Etapa> etapas;

    @Relationship("COMECA_EM")
    private Etapa etapaInicial;

    public List<Etapa> getEtapas() {
        return this.etapas == null ? null : this.etapas.stream().sorted((etapa1, etapa2) -> etapa1.getOrdem() - etapa2.getOrdem()).toList();
    }
  

}
