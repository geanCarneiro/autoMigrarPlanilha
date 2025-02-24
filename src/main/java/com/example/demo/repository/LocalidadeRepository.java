package com.example.demo.repository;

import org.springframework.data.neo4j.repository.Neo4jRepository;

import com.example.demo.model.Localidade;


public interface LocalidadeRepository extends Neo4jRepository<Localidade, String> {
    
}
