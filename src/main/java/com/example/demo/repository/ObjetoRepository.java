package com.example.demo.repository;

import org.springframework.data.neo4j.repository.Neo4jRepository;

import com.example.demo.model.Objeto;

public interface ObjetoRepository extends Neo4jRepository<Objeto, String> {
    
}
