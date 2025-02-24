package com.example.demo.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Relationship;
import org.springframework.data.neo4j.core.schema.Relationship.Direction;

import com.example.demo.dto.PlanoOrcamentarioDTO;

import lombok.Getter;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Node
public class PlanoOrcamentario extends Entidade implements Serializable{
    
    private String codigo;
    private String nome;
    private String descricao;

    public PlanoOrcamentario(PlanoOrcamentarioDTO dto){
        this.setId(dto.id());
        this.codigo = dto.codigo();
        this.nome = dto.nome();
    }
}
