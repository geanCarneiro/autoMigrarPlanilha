package com.example.demo.repository;

import java.util.List;
import java.util.Set;

import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;

import com.example.demo.model.ExecucaoOrcamentaria;


public interface ExecucaoOrcamentariaRepository extends Neo4jRepository<ExecucaoOrcamentaria, String> {
    

    @Query("MATCH (execucao:ExecucaoOrcamentaria)-[:EM]->(ano:Ano)\r\n" + //
                "WHERE ano.ano = $exercicio\r\n" + //
                "RETURN SUM(execucao.orcamento)")
    public Double getTotalOrcadoByAno(String exercicio);

    @Query("MATCH (execucao:ExecucaoOrcamentaria)\r\n" + //
            "RETURN DISTINCT execucao.anoExercicio")
    public Set<Integer> getAnosExercicio();

}
