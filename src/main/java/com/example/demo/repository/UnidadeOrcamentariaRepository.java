package com.example.demo.repository;

import java.util.Optional;

import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;

import com.example.demo.model.UnidadeOrcamentaria;

public interface UnidadeOrcamentariaRepository extends Neo4jRepository<UnidadeOrcamentaria, String> {
    
    @Query("MATCH (unidade:UnidadeOrcamentaria)\r\n" + //
            "WHERE unidade.codigo = $codigo\r\n" + //
            "RETURN unidade")
    public Optional<UnidadeOrcamentaria> findByCodigo(Long codigo);

}
