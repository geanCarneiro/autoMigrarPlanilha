package com.example.demo.model;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Relationship;
import org.springframework.data.neo4j.core.schema.Relationship.Direction;

import com.example.demo.dto.ModuloDto;

import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Node
public class Modulo extends Entidade {
    
    private String nome;
    private String path;
    private String pathId;

    @Relationship(type = "FILHO_DE", direction = Direction.INCOMING)
    private Set<Modulo> filhos;

    public Modulo(ModuloDto dto) {
        this.setId(dto.id());
        this.nome = dto.nome();
        this.pathId = dto.pathId();
        this.filhos = dto.filhos() == null ? null : dto.filhos().stream().map(filhoDto -> new Modulo(filhoDto)).collect(Collectors.toSet());
    }

}
