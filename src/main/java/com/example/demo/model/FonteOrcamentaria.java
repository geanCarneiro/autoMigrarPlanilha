package com.example.demo.model;

import java.io.Serializable;

import org.springframework.data.neo4j.core.schema.Node;

import com.example.demo.dto.FonteOrcamentariaDTO;

import lombok.Getter;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Node
public class FonteOrcamentaria extends Entidade implements Serializable {

    private String codigo;
    private String nome;
    private String descricao;

    public FonteOrcamentaria(String nome) {
        this.nome = nome;
    }

    public FonteOrcamentaria(String codigo, String nome) {
        this.setId(codigo);
        this.nome = nome;
    }

    public FonteOrcamentaria(FonteOrcamentariaDTO dto){
        this.setId(dto.getId());
        this.codigo = dto.getCodigo();
        this.nome = dto.getNome();
        this.descricao = dto.getDescricao();
    }


}