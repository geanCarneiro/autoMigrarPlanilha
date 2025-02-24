package com.example.demo.repository;

import org.springframework.data.neo4j.repository.Neo4jRepository;

import com.example.demo.model.TipoPlano;


public interface TipoPlanoRepository extends Neo4jRepository<TipoPlano, String>{

}
