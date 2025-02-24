package com.example.demo.dto;

import com.example.demo.model.Apontamento;
import com.example.demo.utils.DateTimeUtils;

public record ApontamentoDTO(
    String id,
    String timestamp,
    String texto,
    EtapaDTO etapa,
    CampoDTO campo,
    UsuarioDto usuario,
    GrupoDTO grupo,
    boolean active
) {

    public static ApontamentoDTO parse(Apontamento model) {
        return model == null ? null
        : new ApontamentoDTO(
            model.getId(), 
            DateTimeUtils.formatZonedDateTime(model.getTimestamp()), 
            model.getTexto(), 
            EtapaDTO.parse(model.getEtapa()), 
            CampoDTO.parse(model.getCampo()), 
            UsuarioDto.parse(model.getUsuario()), 
            GrupoDTO.parse(model.getGrupo()),
            model.isActive()
        );

    }

} 
