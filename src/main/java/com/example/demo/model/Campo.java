package com.example.demo.model;

import org.springframework.data.neo4j.core.schema.Node;

import com.example.demo.dto.CampoDTO;

import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Node
public class Campo extends Entidade {

    public String campoId;
    public String nome;


    public static Campo parse(CampoDTO dto) {
        if(dto == null)
            return null;

        Campo campo = new Campo();
        campo.setId(dto.id());
        campo.setCampoId(dto.campoId());
        campo.setNome(dto.nome());

        return campo;

    }
}
