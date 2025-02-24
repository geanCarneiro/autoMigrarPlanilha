package com.example.demo.model;

import java.time.LocalDateTime;
import java.time.ZonedDateTime;

import org.springframework.data.neo4j.core.schema.RelationshipProperties;
import org.springframework.data.neo4j.core.schema.TargetNode;

import com.example.demo.dto.EmStatusDTO;
import com.example.demo.utils.DateTimeUtils;

import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@RelationshipProperties
public class EmStatus extends Entidade {
    
    @TargetNode
    private Status status;

    private ZonedDateTime timestamp;

    public static EmStatus parse (EmStatusDTO dto) {
        if(dto == null) return null;

        EmStatus emStatus = new EmStatus();
        emStatus.setId(dto.id());
        emStatus.status = Status.parse(dto.status());
        emStatus.timestamp = DateTimeUtils.getZonedDateTime(dto.timestamp());

        return emStatus;
    }

}
