package com.example.demo.repository;

import java.util.List;
import java.util.Optional;

import com.example.demo.model.DataMock;
import com.example.demo.model.FonteOrcamentaria;

//public interface FonteOrcamentariaRepository extends Neo4jRepository<FonteOrcamentaria, String> {
public class FonteOrcamentariaRepository{ // extends Neo4jRepository<FonteOrcamentaria, String> {
    
    // @Query("MATCH (execucao:ExecucaoOrcamentaria)<-[vincula:VINCULA]-(fonte:FonteOrcamentaria)-[indicada:INDICADA]->(custo:Custo)\r\n" + //
    //         "WHERE fonte.codigo = $codigo\r\n" + //
    //         "RETURN fonte, collect(custo), collect(indicada), collect(vincula), collect(execucao)")
    public Optional<FonteOrcamentaria> findByCodigo(Long codigo){
        List<FonteOrcamentaria> result = DataMock.noFonteOrcamentarias.stream()
                                            .filter(fonte -> fonte.getCodigo().equals(codigo)).toList();

        return result.isEmpty() ? Optional.empty() : Optional.ofNullable(result.get(0));
    }

    public FonteOrcamentaria save(FonteOrcamentaria fonteOrcamentaria) {
        // TODO Auto-generated method stub
        fonteOrcamentaria.setId(String.valueOf(DataMock.noFonteOrcamentarias.size()));
        DataMock.noFonteOrcamentarias.add(fonteOrcamentaria);
        return fonteOrcamentaria;
    }

}
