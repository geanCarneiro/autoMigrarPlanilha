package com.example.demo.dto;

import java.util.List;

public record CadastroMembroFormDto(
    GrupoDTO grupo,
    OrgaoDto orgao,
    SetorDto setor,
    List<PapelDto> papeis
) {
    
}
