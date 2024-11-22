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
public class UnidadeOrcamentaria extends Entidade implements Serializable {
    
    private Long codigo;
    private String sigla;
    private String nome;

    @Relationship(type = "CONTROLA", direction = Direction.OUTGOING)
    private ArrayList<PlanoOrcamentario> planosOrcamentariosControlados = new ArrayList<>();

    @Relationship(type = "IMPLEMENTA", direction = Direction.OUTGOING)
    private ArrayList<Conta> ContasImplementadas = new ArrayList<>();

    public UnidadeOrcamentaria(Long codigo, String sigla){
        this.codigo = codigo;
        this.sigla = sigla;
    }

    public UnidadeOrcamentaria(Long codigo, String sigla, List<PlanoOrcamentario> planoOrcamentarios, List<Conta> contas) {
        this.codigo = codigo;
        this.sigla = sigla;
        planoOrcamentarios.forEach(this.planosOrcamentariosControlados::add);
        contas.forEach(this.ContasImplementadas::add);
    }

    public void addPlanoSeNaoExistir(PlanoOrcamentario plano) {
        List<PlanoOrcamentario> result = this.planosOrcamentariosControlados.stream()
            .filter(planoControlado -> planoControlado.getCodigo().equals(plano.getCodigo())).toList();

        if(result.size() == 0)
            this.planosOrcamentariosControlados.add(plano);
    }

    public static UnidadeOrcamentaria criar(Long codigo, String sigla) {
        UnidadeOrcamentaria novo = new UnidadeOrcamentaria();
        novo.codigo = codigo;
        novo.sigla = sigla;
        DataMock.noUnidadeOrcamentarias.add(novo);
        return novo;
    }

    public static UnidadeOrcamentaria findOrCreate(Long codigo, String sigla) {
        List<UnidadeOrcamentaria> result = DataMock.noUnidadeOrcamentarias.stream().filter(unidade -> unidade.codigo.equals(codigo)).toList();

        if(result.size() == 0) {
            return criar(codigo, sigla);
        } else {
            return result.get(0);
        }
    }

}
