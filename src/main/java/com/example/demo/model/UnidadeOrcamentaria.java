package com.example.demo.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Relationship;
import org.springframework.data.neo4j.core.schema.Relationship.Direction;

import com.example.demo.dto.UnidadeOrcamentariaDTO;

import lombok.Data;
import lombok.Getter;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Node
public class UnidadeOrcamentaria extends Entidade implements Serializable {
    
    private String codigo;
    private String guid;
    private String sigla;
    private String nome;
    
    public UnidadeOrcamentaria(UnidadeOrcamentariaDTO dto) {
        this.setId(dto.id());
        this.guid = dto.guid();
        this.codigo = dto.codigo();
        this.nome = dto.nome();
        this.sigla = dto.sigla();
    }

}
