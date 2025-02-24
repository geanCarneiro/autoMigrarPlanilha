package com.example.demo.repository;

import org.springframework.data.neo4j.repository.Neo4jRepository;

import com.example.demo.model.Etapa;


public interface EtapaRepository extends Neo4jRepository<Etapa, String> {
    
}
