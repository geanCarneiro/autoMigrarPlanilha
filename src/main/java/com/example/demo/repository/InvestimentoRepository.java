package com.example.demo.repository;

import java.util.Optional;

import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;

import com.example.demo.model.Investimento;
import com.example.demo.model.PlanoOrcamentario;
import com.example.demo.model.UnidadeOrcamentaria;

public interface InvestimentoRepository extends Neo4jRepository<Investimento, String>{
    
    @Query("MATCH (unidade:UnidadeOrcamentaria)-[:IMPLEMENTA]->(investimento:Investimento)<-[:ORIENTA]-(plano:PlanoOrcamentario)\r\n" + //
            "WHERE unidade = $unidade\r\n" + //
            "    AND plano = $plano\r\n" + //
            "RETURN investimento")
    public Optional<Investimento> findByFilter(UnidadeOrcamentaria unidade, PlanoOrcamentario plano);

}
