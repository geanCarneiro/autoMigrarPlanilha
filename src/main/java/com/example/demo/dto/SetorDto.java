package com.example.demo.dto;

import com.example.demo.dto.acessocidadaoapi.SetorACResponseDto;
import com.example.demo.model.Setor;

import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
 
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SetorDto {
    private String id;
    private String guid;
    private String nome;
    private String sigla;

    private OrgaoDto orgao;
    
    public SetorDto(SetorACResponseDto setorAC){
        this.guid = setorAC.guid();
        this.nome = setorAC.nome();
        this.sigla = setorAC.nomeCurto();

    }

    public SetorDto(Setor setor){
        this.id = setor.getId();
        this.guid = setor.getGuid();
        this.nome = setor.getNome();
        this.sigla = setor.getSigla();
        this.orgao = setor.getOrgao() == null ? null : new OrgaoDto(setor.getOrgao());
    }

}
