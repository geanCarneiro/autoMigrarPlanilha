package com.example.demo.repository;

import java.util.List;
import java.util.Optional;

import com.example.demo.model.Ano;
import com.example.demo.model.Custo;
import com.example.demo.model.DataMock;
import com.example.demo.model.FonteOrcamentaria;
import com.example.demo.model.Objeto;


//public interface CustoRepository extends Neo4jRepository<Custo, String> {
public class CustoRepository{// extends Neo4jRepository<Custo, String> {
    
    // @Query("MATCH (c:Custo) WHERE (c.anoExercicio = $exercicio) RETURN c\r\n")
    // public List<Custo> findByExercicio(String exercicio);

    // @Query("MATCH (fonte:FonteOrcamentaria)-[indicada:INDICADA]->(custo:Custo)-[estimado:ESTIMADO]->(objeto:Objeto),\r\n" + //
    //         "   (custo)-[em:EM]->(ano:Ano)\r\n" + //
    //             "WHERE objeto = $objeto\r\n" + //
    //             "    AND ano = $anoExecucao\r\n" + //
    //             "    AND fonte = $fonte\r\n" + //
    //             "RETURN custo, collect(em), collect(ano), collect(fonte), collect(indicada), collect(estimado), collect(objeto)")
    public Optional<Custo> findByFilter(Ano anoExecucao, Objeto objeto, FonteOrcamentaria fonte){
        List<Custo> result = DataMock.noCustos.stream()
                            .filter(custo -> {
                                return custo.getAnoExercicio().getId().equals(anoExecucao.getId())
                                && custo.getObjetoEstimado().getId().equals(objeto.getId())
                                && custo.getFonteOrcamentariaIndicadora().getCodigo().equals(fonte.getCodigo());
                            }).toList();
        
        if(result.isEmpty()) {
            return Optional.empty();
        } else {
            return Optional.ofNullable(result.get(0));
        }
    }

    public Custo save(Custo custos) {
        // TODO Auto-generated method stub
        custos.setId(String.valueOf(DataMock.noCustos.size()));
        DataMock.noCustos.add(custos);
        return custos;
    }

    public void saveAll(List<Custo> custos) {
        custos.forEach(this::save);
    }
}
