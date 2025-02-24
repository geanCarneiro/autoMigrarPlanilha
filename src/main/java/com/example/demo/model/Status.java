package com.example.demo.model;

import org.springframework.data.neo4j.core.schema.Node;

import com.example.demo.dto.StatusDTO;

import lombok.Getter;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Node
public class Status extends Entidade {
    
    private String nome;
    private StatusEnum statusId;

    public static Status parse (StatusDTO dto) {
        if(dto == null) return null;

        Status status = new Status();
        status.setId(dto.id());
        status.nome = dto.nome();
        status.statusId = StatusEnum.valueOf(dto.statusId());

        return status;
    }
 
}
