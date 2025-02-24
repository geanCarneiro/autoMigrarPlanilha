package com.example.demo.model;

import java.util.Arrays;
import java.util.List;

import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Relationship;
import org.springframework.data.neo4j.core.schema.Relationship.Direction;

import com.example.demo.dto.OrgaoDto;
import com.example.demo.dto.acessocidadaoapi.UnidadesACResponseDto;

import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Node
public class Orgao extends Entidade{
    
    private String guid;

    private String sigla;
    private String nome;
    
    public Orgao(UnidadesACResponseDto unidadeAC){
        this.guid = unidadeAC.guid();
        this.sigla = unidadeAC.sigla();
        this.nome = unidadeAC.nomeFantasia();
    }

    public Orgao(OrgaoDto dto){
        this.setId(dto.id());
        this.guid = dto.guid();
        this.sigla = dto.sigla();
        this.nome = dto.nome();
    }



}
