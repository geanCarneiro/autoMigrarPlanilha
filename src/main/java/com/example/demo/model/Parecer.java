package com.example.demo.model;

import java.time.ZonedDateTime;

import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Relationship;

import com.example.demo.dto.ParecerDTO;
import com.example.demo.utils.DateTimeUtils;

import lombok.Getter;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Node
public class Parecer extends Entidade {
    
    private ZonedDateTime timestamp;
    private String texto;

    @Relationship("EM")
    private Etapa etapa;
        
    @Relationship("FEITO_POR")
    private Usuario usuario;

    @Relationship("FEITO_POR")
    private Grupo grupo;

    public static Parecer parse(ParecerDTO dto) {
        if (dto == null) 
            return null;
        
        Parecer parecer = new Parecer();
        parecer.setId(dto.id());
        parecer.setTimestamp(dto.timestamp() == null ? null : DateTimeUtils.getZonedDateTime(dto.timestamp()));
        parecer.setTexto(dto.texto());
        parecer.setEtapa(Etapa.parse(dto.etapa()));
        parecer.setUsuario(Usuario.parse(dto.feitoPor()));
        parecer.setGrupo(Grupo.parse(dto.doGrupo()));

        return parecer;

    }

}
