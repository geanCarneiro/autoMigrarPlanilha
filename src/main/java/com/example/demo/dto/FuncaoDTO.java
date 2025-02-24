package com.example.demo.dto;

import com.example.demo.model.Funcao;

import lombok.Getter;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FuncaoDTO {
    private String id;
    private String nome;

    public FuncaoDTO(Funcao funcao) {
        this.id = funcao.getId();
        this.nome = funcao.getNome();
    }
}
