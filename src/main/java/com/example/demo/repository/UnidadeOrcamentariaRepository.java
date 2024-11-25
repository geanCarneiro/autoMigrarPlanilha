package com.example.demo.repository;

import java.util.List;
import java.util.Optional;

import com.example.demo.model.DataMock;
import com.example.demo.model.UnidadeOrcamentaria;

//public interface UnidadeOrcamentariaRepository extends Neo4jRepository<UnidadeOrcamentaria, String> {
public class UnidadeOrcamentariaRepository{// extends Neo4jRepository<UnidadeOrcamentaria, String> {
    
    // @Query("MATCH (plano:PlanoOrcamentario)<-[controla:CONTROLA]-(unidade:UnidadeOrcamentaria)-[implementa:IMPLEMENTA]->(conta:Conta)\r\n" + //
    //             "WHERE unidade.codigo = $codigo\r\n" + //
    //             "RETURN unidade, collect(plano), collect(controla), collect(implementa), collect(conta)")
    public Optional<UnidadeOrcamentaria> findByCodigo(Long codigo){
        List<UnidadeOrcamentaria> result = DataMock.noUnidadeOrcamentarias.stream()
            .filter(unidade -> unidade.getCodigo().equals(codigo)).toList();
        
        return result.isEmpty() ? Optional.empty() : Optional.ofNullable(result.get(0));
    }

    public UnidadeOrcamentaria save(UnidadeOrcamentaria unidade) {
        // TODO Auto-generated method stub
        unidade.setId(String.valueOf(DataMock.noUnidadeOrcamentarias.size()));
        DataMock.noUnidadeOrcamentarias.add(unidade);
        return unidade;
    }

}
