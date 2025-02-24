package com.example.demo.model;

import org.springframework.data.neo4j.core.schema.Node;

import com.example.demo.dto.LocalidadeDto;

import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Node
public class Localidade extends Entidade {
    
    private String nome;
    
    public Localidade(LocalidadeDto dto) {
        this.setId(dto.id());
        this.nome = dto.nome();
    }


}
