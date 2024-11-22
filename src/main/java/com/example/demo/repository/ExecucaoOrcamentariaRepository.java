package com.example.demo.repository;

import java.util.Optional;

import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;

import com.example.demo.model.Ano;
import com.example.demo.model.Conta;
import com.example.demo.model.ExecucaoOrcamentaria;
import com.example.demo.model.FonteOrcamentaria;

public interface ExecucaoOrcamentariaRepository extends Neo4jRepository<ExecucaoOrcamentaria, String>{

    @Query("MATCH (conta:Conta)<-[:DELIMITA]-(execucao:ExecucaoOrcamentaria)<-[:VINCULA]-(fonte:FonteOrcamentaria),\r\n" + //
            "    (execucao)-[:EM]->(ano:Ano)\r\n" + //
            "WHERE conta = $conta\r\n" + //
            "    AND ano = $ano\r\n" + //
            "    AND fonte = $fonte\r\n" + //
            "RETURN execucao")
    public Optional<ExecucaoOrcamentaria> findByFilter(Ano ano, Conta conta, FonteOrcamentaria fonte);
    
}
