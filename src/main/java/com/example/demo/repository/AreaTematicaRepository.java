package com.example.demo.repository;

import org.springframework.data.neo4j.repository.Neo4jRepository;

import com.example.demo.model.AreaTematica;


public interface AreaTematicaRepository extends Neo4jRepository<AreaTematica, String> {
    
    

}
