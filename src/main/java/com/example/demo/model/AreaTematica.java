package com.example.demo.model;

import org.springframework.data.neo4j.core.schema.Node;

import com.example.demo.dto.AreaTematicaDto;

import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Node
public class AreaTematica extends Entidade {
    
    private String nome;

    public AreaTematica(AreaTematicaDto dto) {
        this.setId(dto.id());
        this.nome = dto.nome();
    }

}
