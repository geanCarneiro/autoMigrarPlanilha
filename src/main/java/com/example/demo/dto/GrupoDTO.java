package com.example.demo.dto;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.example.demo.model.Grupo;

import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GrupoDTO {
    private String id;
    private String icone;
    private String sigla;
    private String nome;
    private String descricao;
    private Boolean podeVerTodasUnidades;

    
    private Set<UsuarioDto> membros; 
    private Set<SetorDto> setoresMembros;

    private Set<PodeDto> permissoes;


    public GrupoDTO(Grupo grupo) {
        this.id = grupo.getId();
        this.icone = grupo.getIcone();
        this.sigla = grupo.getSigla();
        this.nome = grupo.getNome();
        this.descricao = grupo.getDescricao();
        this.podeVerTodasUnidades = grupo.isPodeVerTodasUnidades();
        
        if(grupo.getMembros() != null)
            this.membros = grupo.getMembros().stream().map(usuario -> new UsuarioDto(usuario)).collect(Collectors.toSet());

        if(grupo.getPermissoes() != null)
            this.permissoes = grupo.getPermissoes().stream().map(permissao -> new PodeDto(permissao)).collect(Collectors.toSet());
    }

    public static GrupoDTO parse (Grupo model) {
        return model == null ? null
        : new GrupoDTO(model);
    }

}
