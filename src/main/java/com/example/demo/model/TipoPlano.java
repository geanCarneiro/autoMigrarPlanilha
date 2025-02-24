package com.example.demo.model;

import org.springframework.data.neo4j.core.schema.Node;

import com.example.demo.dto.TipoPlanoDto;

import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Node
public class TipoPlano extends Entidade {
    
    private String nome;
    private String sigla;

    public TipoPlano(TipoPlanoDto dto) {
        this.setId(dto.id());
        this.nome = dto.nome();
        this.sigla = dto.sigla();
    }

}
