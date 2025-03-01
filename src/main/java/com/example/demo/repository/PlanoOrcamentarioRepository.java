package com.example.demo.repository;

import java.util.List;

import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;

import com.example.demo.model.PlanoOrcamentario;


public interface PlanoOrcamentarioRepository extends Neo4jRepository<PlanoOrcamentario, String> {
    
    @Query("MATCH (plano:PlanoOrcamentario) RETURN plano ORDER BY plano.codigo")
    public List<PlanoOrcamentario> getAllSimples();
    
    @Query("MATCH (plano:PlanoOrcamentario)\r\n" + //
            "WHERE elementId(plano) = $idPlano\r\n" + //
            "RETURN toString(plano.codigo)")
    public String getCodById(String idPlano);
}
