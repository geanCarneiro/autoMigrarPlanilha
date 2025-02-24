package com.example.demo.model;

import java.io.Serializable;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Relationship;
import org.springframework.data.neo4j.core.schema.Relationship.Direction;

import com.example.demo.dto.CustoDTO;

import lombok.Getter;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Node
public class Custo extends Entidade implements Serializable {
     
    private Integer anoExercicio;

    @Relationship(type = "INDICADA_POR", direction = Direction.OUTGOING)
    private Set<IndicadaPor> indicadaPor;

    public Custo(CustoDTO dto) {
        this.setId(dto.getId());
        this.anoExercicio = dto.getAnoExercicio();
        this.indicadaPor = dto.getIndicadaPor().stream().map(indicadaPorDto -> new IndicadaPor(indicadaPorDto)).collect(Collectors.toSet());
    }

}
