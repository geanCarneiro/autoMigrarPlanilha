package com.example.demo.repository;

import java.util.List;
import java.util.Optional;

import com.example.demo.model.DataMock;
import com.example.demo.model.PlanoOrcamentario;

//public interface PlanoOrcamentarioRepository extends Neo4jRepository<PlanoOrcamentario, String>{
public class PlanoOrcamentarioRepository {// extends Neo4jRepository<PlanoOrcamentario, String>{
    

    // @Query("MATCH (plano:PlanoOrcamentario)-[orienta:ORIENTA]->(conta:Conta)\r\n" + //
    //         "WHERE plano.codigo = $codigo\r\n" + //
    //         "RETURN plano, collect(orienta), collect(conta)")
    public Optional<PlanoOrcamentario> findByCodigo(Long codigo){
        List<PlanoOrcamentario> result = DataMock.noPlanoOrcamentarios.stream()
            .filter(plano -> plano.getCodigo().equals(codigo)).toList();
        
        return result.isEmpty() ? Optional.empty() : Optional.ofNullable(result.get(0));
    }

    public PlanoOrcamentario save(PlanoOrcamentario planoOrcamentario) {
        // TODO Auto-generated method stub
        planoOrcamentario.setId(String.valueOf(DataMock.noPlanoOrcamentarios.size()));
        DataMock.noPlanoOrcamentarios.add(planoOrcamentario);
        return planoOrcamentario;
    }
}
