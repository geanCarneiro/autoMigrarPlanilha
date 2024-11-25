package com.example.demo.repository;

import java.util.List;
import java.util.Optional;

import com.example.demo.model.DataMock;
import com.example.demo.model.Investimento;
import com.example.demo.model.PlanoOrcamentario;
import com.example.demo.model.UnidadeOrcamentaria;

//public interface InvestimentoRepository extends Neo4jRepository<Investimento, String>{
public class InvestimentoRepository {//extends Neo4jRepository<Investimento, String>{
    
    // @Query("MATCH (unidade:UnidadeOrcamentaria)-[implementa:IMPLEMENTA]->(investimento:Investimento)<-[orienta:ORIENTA]-(plano:PlanoOrcamentario),\r\n" + //
    //         "(objeto:Objeto)-[custeado:CUSTEADO]->(investimento)<-[delimita:DELIMITA]-(execucao:ExecucaoOrcamentaria)\r\n" + //
    //         "WHERE unidade = $unidade\r\n" + //
    //         "    AND plano = $plano\r\n" + //
    //         "RETURN investimento, collect(implementa), collect(unidade), collect(orienta), collect(plano)," +
    //         "   collect(objeto), collect(custeado), collect(delimita), collect(execucao)")
    public Optional<Investimento> findByFilter(UnidadeOrcamentaria unidade, PlanoOrcamentario plano){
        List<Investimento> result = DataMock.noInvestimentos.stream()
            .filter(inv -> inv.getUnidadeOrcamentariaImplementadora().getCodigo().equals(unidade.getCodigo())
                && inv.getPlanoOrcamentarioOrientador().getCodigo().equals(plano.getCodigo())).toList();
        
        return result.isEmpty() ? Optional.empty() : Optional.ofNullable(result.get(0));
    }

    public Investimento save(Investimento investimento) {
        // TODO Auto-generated method stub
        investimento.setId(String.valueOf(DataMock.noInvestimentos.size()));
        DataMock.noInvestimentos.add(investimento);
        return investimento;
    }

}
