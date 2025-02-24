package com.example.demo.model;

import org.springframework.data.neo4j.core.schema.Node;

import com.example.demo.dto.AvatarDTO;

import lombok.Getter;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Node
public class Avatar extends Entidade{
    
    private String blob;

    public Avatar(AvatarDTO dto) {
        this.setId(dto.getId());
        this.blob = dto.getBlob();
    }
}
