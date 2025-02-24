package com.example.demo.model;

import java.time.ZonedDateTime;

import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Relationship;

import com.example.demo.dto.ApontamentoDTO;
import com.example.demo.utils.DateTimeUtils;

import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Node
public class Apontamento extends Entidade{
    
    private ZonedDateTime timestamp;
    private String texto;
    private boolean active;

    @Relationship("EM")
    private Etapa etapa;
    
    @Relationship("SOBRE")
    private Campo campo;
    
    @Relationship("FEITO_POR")
    private Usuario usuario;

    @Relationship("FEITO_POR")
    private Grupo grupo;

    public static Apontamento parse(ApontamentoDTO dto) {
        if (dto == null) 
            return null;
        
        Apontamento apontamento = new Apontamento();
        apontamento.setId(dto.id());
        apontamento.setTimestamp(dto.timestamp() == null ? null : DateTimeUtils.getZonedDateTime(dto.timestamp()));
        apontamento.setTexto(dto.texto());
        apontamento.setEtapa(Etapa.parse(dto.etapa()));
        apontamento.setCampo(Campo.parse(dto.campo()));
        apontamento.setUsuario(Usuario.parse(dto.usuario()));
        apontamento.setGrupo(Grupo.parse(dto.grupo()));
        apontamento.setActive(dto.active());

        return apontamento;

    }


}
