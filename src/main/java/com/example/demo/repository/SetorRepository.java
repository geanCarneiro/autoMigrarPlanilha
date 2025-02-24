package com.example.demo.repository;

import java.util.Optional;

import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;

import com.example.demo.model.Setor;


public interface SetorRepository extends Neo4jRepository<Setor, String> {
    
    @Query("MATCH (setor:Setor) WHERE setor.guid = $guid RETURN setor")
    public Optional<Setor> findByGuid(String guid);

}
