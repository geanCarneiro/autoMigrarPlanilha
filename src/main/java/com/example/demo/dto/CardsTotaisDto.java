package com.example.demo.dto;

public record CardsTotaisDto(
    Double previsto,
    Double contratado,
    Double orcado,
    Double autorizado,
    Double empenhado,
    Double liquidado,
    Double dispSemReserva,
    Double pago
) {
    
}
