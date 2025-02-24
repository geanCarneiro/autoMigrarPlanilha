package com.example.demo.repository;

import org.springframework.data.neo4j.repository.Neo4jRepository;

import com.example.demo.model.Campo;


public interface CampoRepository extends Neo4jRepository<Campo, String> {
    
}
