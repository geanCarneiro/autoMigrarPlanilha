package com.example.demo.repository;

import java.util.Optional;

import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;

import com.example.demo.model.Ano;

public interface AnoRepository extends Neo4jRepository<Ano, String>{
    
    @Query("MATCH (ano:Ano)\r\n" + //
            "WHERE ano.ano = $ano\r\n" + //
            "RETURN ano")
    public Optional<Ano> findByAno(String ano);

}
