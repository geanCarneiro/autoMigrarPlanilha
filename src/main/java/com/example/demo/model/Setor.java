package com.example.demo.model;

import java.util.List;

import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Relationship;
import org.springframework.data.neo4j.core.schema.Relationship.Direction;

import com.example.demo.dto.SetorDto;

import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Node
public class Setor extends Entidade {
    private String guid;
    private String nome;
    private String sigla;

    @Relationship(type = "PERTENCE_A")
    private Orgao orgao;

    public Setor(SetorDto dto) {
        this.setId(dto.getId());
        this.guid = dto.getGuid();
        this.nome = dto.getNome();
        this.sigla = dto.getSigla();
        this.orgao = dto.getOrgao() == null ? null : new Orgao(dto.getOrgao());
    }
}
