package com.example.demo.repository;

import java.util.List;
import java.util.Optional;

import com.example.demo.model.Ano;
import com.example.demo.model.Conta;
import com.example.demo.model.DataMock;
import com.example.demo.model.ExecucaoOrcamentaria;
import com.example.demo.model.FonteOrcamentaria;

// public interface ExecucaoOrcamentariaRepository extends Neo4jRepository<ExecucaoOrcamentaria, String>{
public class ExecucaoOrcamentariaRepository{ // extends Neo4jRepository<ExecucaoOrcamentaria, String>{

    // @Query("MATCH (conta:Conta)<-[delimita:DELIMITA]-(execucao:ExecucaoOrcamentaria)<-[vincula:VINCULA]-(fonte:FonteOrcamentaria),\r\n" + //
    //         "    (execucao)-[em:EM]->(ano:Ano)\r\n" + //
    //         "WHERE conta = $conta\r\n" + //
    //         "    AND ano = $ano\r\n" + //
    //         "    AND fonte = $fonte\r\n" + //
    //         "RETURN execucao, collect(em), collect(ano), collect(delimita), collect(conta), collect(vincula), collect(fonte)")
    public Optional<ExecucaoOrcamentaria> findByFilter(Ano ano, Conta conta, FonteOrcamentaria fonte){
        List<ExecucaoOrcamentaria> result = DataMock.noExecucaoOrcamentarias.stream()
                                            .filter(exec -> exec.getAnoExercicio().getId().equals(ano.getId())
                                                    && exec.getContaDelimitada().getId().equals(conta.getId())
                                                    && exec.getFonteOrcamentariaVinculadora().getCodigo().equals(fonte.getCodigo()))
                                                    .toList();
        
        return result.isEmpty() ? Optional.empty() : Optional.ofNullable(result.get(0));
    }

    public ExecucaoOrcamentaria save(ExecucaoOrcamentaria execucaoOrcamentaria) {
        // TODO Auto-generated method stub
        execucaoOrcamentaria.setId(String.valueOf(DataMock.noExecucaoOrcamentarias.size()));
        DataMock.noExecucaoOrcamentarias.add(execucaoOrcamentaria);
        return execucaoOrcamentaria;  
    }
    
    
}
