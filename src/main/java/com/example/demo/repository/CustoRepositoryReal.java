package com.example.demo.repository;

import org.springframework.data.neo4j.repository.Neo4jRepository;

import com.example.demo.model.Custo;

public interface CustoRepositoryReal extends Neo4jRepository<Custo, String> {
    
}
