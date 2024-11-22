package com.example.demo.repository;

import java.util.Optional;

import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;

import com.example.demo.model.FonteOrcamentaria;

public interface FonteOrcamentariaRepository extends Neo4jRepository<FonteOrcamentaria, String> {
    
    @Query("MATCH (fonte:FonteOrcamentaria)\r\n" + //
            "WHERE fonte.codigo = $codigo\r\n" + //
            "RETURN fonte")
    public Optional<FonteOrcamentaria> findByCodigo(Long codigo);

}
