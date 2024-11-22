package com.example.demo.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Relationship;
import org.springframework.data.neo4j.core.schema.Relationship.Direction;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Node
public class PlanoOrcamentario extends Entidade implements Serializable{
    
    private Long codigo;
    private String nome;
    private String descricao;

    @Relationship(type = "ORIENTA", direction = Direction.OUTGOING)
    private ArrayList<Conta> contasOrientadas = new ArrayList<>();

    public PlanoOrcamentario(Long codigo, String nome){
        this.codigo = codigo;
        this.nome = nome;
    }

    public PlanoOrcamentario(Long codigo, String nome, List<Conta> contas) {
        this.codigo = codigo;
        this.nome = nome;
        contas.forEach(this.contasOrientadas::add);
    }

    public static PlanoOrcamentario criar(Long codigo, String nome) {
        PlanoOrcamentario novo = new PlanoOrcamentario();
        novo.codigo = codigo;
        novo.nome = nome;
        DataMock.noPlanoOrcamentarios.add(novo);
        return novo;
    }

    public static PlanoOrcamentario findOrCreate(Long codigo, String nome){

        List<PlanoOrcamentario> result = DataMock.noPlanoOrcamentarios.stream().filter(plano -> plano.codigo.equals(codigo)).toList();

        if(result.size() == 0) {
            return criar(codigo, nome);
        } else {
            return result.get(0);
        }
    } 

}
