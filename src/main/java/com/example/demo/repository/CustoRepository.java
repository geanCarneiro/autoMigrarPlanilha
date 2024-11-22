package com.example.demo.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;

import com.example.demo.model.Ano;
import com.example.demo.model.Custo;
import com.example.demo.model.FonteOrcamentaria;
import com.example.demo.model.Objeto;


public interface CustoRepository extends Neo4jRepository<Custo, String> {
    
    @Query("MATCH (c:Custo) WHERE (c.anoExercicio = $exercicio) RETURN c\r\n")
    public List<Custo> findByExercicio(String exercicio);

    @Query("MATCH (fonte:FonteOrcamentaria)-[:INDICADA]->(custo:Custo)-[:ESTIMADO]->(objeto:Objeto),\r\n" + //
            "   (custo)-[:EM]->(ano:Ano)\r\n" + //
                "WHERE objeto = $objeto\r\n" + //
                "    AND ano = $anoExecucao\r\n" + //
                "    AND fonte = $fonte\r\n" + //
                "RETURN custo")
    public Optional<Custo> findByFilter(Ano anoExecucao, Objeto objeto, FonteOrcamentaria fonte);
}
