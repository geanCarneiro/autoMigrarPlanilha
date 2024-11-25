package com.example.demo.repository;

import java.util.List;
import java.util.Optional;

import com.example.demo.model.Ano;
import com.example.demo.model.DataMock;

// public interface AnoRepository extends Neo4jRepository<Ano, String>{
public class AnoRepository {// extends Neo4jRepository<Ano, String>{
    
    // @Query("MATCH (ano:Ano)\r\n" + //
    //         "WHERE ano.ano = $ano\r\n" + //
    //         "RETURN ano")
    public Optional<Ano> findByAno(String _ano){
        List<Ano> result = DataMock.noAnos.stream()
                                .filter(ano -> ano.getAno().equals(_ano))
                                .toList();

        if(result.isEmpty())
            return Optional.empty();
        else 
            return Optional.ofNullable(result.get(0));
    }

    public Ano save(Ano ano) {
        ano.setId(String.valueOf(DataMock.noAnos.size()));
        DataMock.noAnos.add(ano);
        return ano;
    }

}
