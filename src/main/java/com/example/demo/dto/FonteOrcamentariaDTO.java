package com.example.demo.dto;

import java.io.Serializable;
import java.util.List;

import com.example.demo.model.FonteOrcamentaria;

import lombok.Getter;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FonteOrcamentariaDTO implements Serializable {

    private String id;
    private String nome;
    private String codigo;
    private String descricao;

    public FonteOrcamentariaDTO ( FonteOrcamentaria fonte ) {
        this.id = fonte.getId();
        this.nome = fonte.getNome();
        this.codigo = fonte.getCodigo();
        this.descricao = fonte.getDescricao();
    }

    public static FonteOrcamentariaDTO parse(FonteOrcamentaria fonte) {
        return fonte == null ? null : new FonteOrcamentariaDTO(fonte);
    }
}
