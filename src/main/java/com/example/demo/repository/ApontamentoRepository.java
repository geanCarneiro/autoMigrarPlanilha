package com.example.demo.repository;

import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;

import com.example.demo.model.Apontamento;


public interface ApontamentoRepository extends Neo4jRepository<Apontamento, String> {
    
    @Query("MATCH (a:Apontamento)\r\n" + //
                "MATCH (o:Objeto)\r\n" + //
                "WHERE elementId(a) = $apontamentoId AND elementId(o) = $objetoId\r\n" + //
                "MERGE (o)-[:POSSUI]->(a)")
    public void mergeObjetoApontamento(String objetoId, String apontamentoId);

}
