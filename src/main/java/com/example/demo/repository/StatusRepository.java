package com.example.demo.repository;

import java.util.List;

import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;

import com.example.demo.model.Status;


public interface StatusRepository extends Neo4jRepository<Status, String> {
    

    @Query("MATCH (:Objeto)-[:EM]->(status:Status) \r\n" + //
                "RETURN DISTINCT status")
    public List<Status> findAllStatusObjeto();

}
