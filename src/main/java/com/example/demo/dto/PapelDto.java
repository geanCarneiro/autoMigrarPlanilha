package com.example.demo.dto;

import com.example.demo.dto.acessocidadaoapi.PapelACResponseDto;

public record PapelDto(
    String id,
    String guid,
    String nome,
    String agenteSub,
    String agenteNome
) {
    
    public PapelDto(PapelACResponseDto papelAC){
        this(null, papelAC.Guid(), papelAC.Nome(), papelAC.AgentePublicoSub(), papelAC.AgentePublicoNome());
    }

}
