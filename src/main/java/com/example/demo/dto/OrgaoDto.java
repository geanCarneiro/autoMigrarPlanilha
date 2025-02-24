package com.example.demo.dto;

import com.example.demo.model.Orgao;

public record OrgaoDto(
        String id,
        String guid,
        String sigla,
        String nome
    ) {
        
    public OrgaoDto(Orgao orgao){
        this(orgao.getId(), orgao.getGuid(), orgao.getSigla(), orgao.getNome());
    }

}
