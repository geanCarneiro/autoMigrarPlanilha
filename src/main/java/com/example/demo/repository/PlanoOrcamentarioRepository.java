package com.example.demo.repository;

import java.util.Optional;

import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;

import com.example.demo.model.PlanoOrcamentario;

public interface PlanoOrcamentarioRepository extends Neo4jRepository<PlanoOrcamentario, String>{
    

    @Query("MATCH (plano:PlanoOrcamentario)\r\n" + //
            "WHERE plano.codigo = $codigo\r\n" + //
            "RETURN plano")
    public Optional<PlanoOrcamentario> findByCodigo(Long codigo);
}
