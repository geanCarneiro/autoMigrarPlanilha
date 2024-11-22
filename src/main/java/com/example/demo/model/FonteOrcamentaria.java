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
public class FonteOrcamentaria extends Entidade implements Serializable {

    private Long codigo;
    private String nome;
    private String descricao;

    @Relationship(type = "INDICADA", direction = Direction.OUTGOING)
    private ArrayList<Custo> custosIndicados = new ArrayList<>();

    @Relationship(type = "VINCULA", direction = Direction.OUTGOING)
    private ArrayList<ExecucaoOrcamentaria> execucoesOrcamentariaVinculadas = new ArrayList<>();

    public FonteOrcamentaria(String nome) {
        this.nome = nome;
    }

    public FonteOrcamentaria(Long codigo, String nome) {
        this.codigo = codigo;
        this.nome = nome;
    }

    public FonteOrcamentaria(String codigo, String nome, List<Custo> custos, List<ExecucaoOrcamentaria> execucoes) {
        this.setId(codigo);
        this.nome = nome;
        custos.forEach(this.custosIndicados::add);
        execucoes.forEach(this.execucoesOrcamentariaVinculadas::add);
    }

    public static FonteOrcamentaria criar(String codigo, String nome){
        FonteOrcamentaria novo = new FonteOrcamentaria();
        novo.setId(codigo);
        novo.nome = nome;
        DataMock.noFonteOrcamentarias.add(novo);
        return novo;
    }
    
    public static FonteOrcamentaria findOrCreate(String codigo, String nome) {
        List<FonteOrcamentaria> result = DataMock.noFonteOrcamentarias.stream().filter(fonte -> fonte.getId().equals(codigo)).toList();

        if(result.size() == 0)
            return criar(codigo, nome);
        else
            return result.get(0);
    }

}