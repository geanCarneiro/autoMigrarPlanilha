package com.example.demo.dto.acessocidadaoapi;

public record PapelACResponseDto(
    String Guid,
    String Nome,
    String Tipo,
    String LotacaoGuid,
    String AgentePublicoSub,
    String AgentePublicoNome,
    Boolean Prioritario
) {
    
}
