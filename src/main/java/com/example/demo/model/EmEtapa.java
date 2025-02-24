package com.example.demo.model;

import org.springframework.data.neo4j.core.schema.RelationshipProperties;
import org.springframework.data.neo4j.core.schema.TargetNode;

import com.example.demo.dto.EmEtapaDTO;
import com.example.demo.dto.projection.EmEtapaProjection;

import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@RelationshipProperties
public class EmEtapa extends Entidade {
    
    @TargetNode
    private Etapa etapa;

    private String atividade;

    private boolean devolvido;

    public static EmEtapa parse(EmEtapaDTO dto) {
        if(dto == null) {
            return null;
        }

        EmEtapa emEtapa = new EmEtapa();
        emEtapa.setId(dto.id());
        emEtapa.etapa = Etapa.parse(dto.etapa());
        emEtapa.atividade = dto.atividade();
        emEtapa.isDevolvido();

        return emEtapa;
    }

    public static EmEtapa parse (EmEtapaProjection projection) {
        if(projection == null) return null;

        EmEtapa emEtapa = new EmEtapa();
        emEtapa.setId(projection.getId());
        emEtapa.setEtapa(projection.getEtapa());

        return emEtapa;
    }


}
