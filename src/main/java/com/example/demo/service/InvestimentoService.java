package com.example.demo.service;

import java.text.Normalizer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.ExampleMatcher.StringMatcher;
import org.springframework.data.neo4j.core.Neo4jOperations;
import org.springframework.data.repository.query.FluentQuery.FetchableFluentQuery;
import org.springframework.stereotype.Service;

import com.example.demo.dto.projection.TiraInvestimentoProjection;
import com.example.demo.model.Custo;
import com.example.demo.model.ExecucaoOrcamentaria;
import com.example.demo.model.IndicadaPor;
import com.example.demo.model.Investimento;
import com.example.demo.model.Objeto;
import com.example.demo.model.PlanoOrcamentario;
import com.example.demo.model.UnidadeOrcamentaria;
import com.example.demo.model.VinculadaPor;
import com.example.demo.repository.InvestimentoRepository;
import com.example.demo.utils.DataListResult;


@Service
public class InvestimentoService {
    
    @Autowired
    private InvestimentoRepository repository;

    @Autowired
    private Neo4jOperations neo4jOperations;

    @Autowired
    private ObjetoService objetoService;

    public Investimento findOrCreateByUoPo(Investimento investimento){

        PlanoOrcamentario poProbe = new PlanoOrcamentario();
        poProbe.setCodigo(investimento.getPlanoOrcamentario().getCodigo());

        UnidadeOrcamentaria uoProbe = new UnidadeOrcamentaria();
        uoProbe.setCodigo(investimento.getUnidadeOrcamentariaImplementadora().getCodigo());

        Investimento invProbe = new Investimento();
        invProbe.setPlanoOrcamentario(poProbe);
        invProbe.setUnidadeOrcamentariaImplementadora(uoProbe);

        return repository.findBy(Example.of(invProbe), q -> q.first()).orElse(investimento);

    }

    public void saveAll(List<Investimento> investimentos) {
        repository.saveAll(investimentos);
    }

    public Investimento save(Investimento investimento) {
        return repository.save(investimento);
    }

    public DataListResult<TiraInvestimentoProjection> findAllTiraBy(
            String nome, String codUnidade, String codPO,
            Integer exercicio, String idFonte, Pageable pageable
        ) {

        String cypherBase = "MATCH (inv:Investimento)<-[:CUSTEADO]-(obj:Objeto),\r\n" + //
                            "        (po:PlanoOrcamentario)-[:ORIENTA]->(inv)<-[:IMPLEMENTA]-(unidade:UnidadeOrcamentaria)\r\n" + //
                            "WHERE ($idPo IS NULL OR elementId(po) = $idPo)\r\n" + //
                            "    AND ( $idUnidade IS NULL OR elementId(unidade) = $idUnidade )\r\n" + //
                            "    AND NOT EXISTS((obj)-[:EM]->(:Etapa))\r\n" + //
                            "    AND ($nome IS NULL OR apoc.text.clean(inv.nome) CONTAINS apoc.text.clean($nome))\r\n" + //
                            "CALL (obj) {\r\n" + //
                            "    MATCH (obj)<-[:ESTIMADO]-(custo:Custo)-[indicada_por:INDICADA_POR]->(fonteCusto:FonteOrcamentaria)\r\n" + //
                            "    WHERE ($idFonte IS NULL OR elementId(fonteCusto) = $idFonte)\r\n" + //
                            "        AND ($exercicio IS NULL OR custo.anoExercicio = $exercicio)\r\n" + //
                            "    RETURN \r\n" + //
                            "        sum(indicada_por.previsto) AS totalPrevisto, \r\n" + //
                            "        sum(indicada_por.contratado) AS totalContratado \r\n" + //
                            "} \r\n" + //
                            "CALL (inv) {\r\n" + //
                            "    MATCH (inv)<-[:DELIMITA]-(exec:ExecucaoOrcamentaria)-[vinculada_por:VINCULADA_POR]->(fonteExec:FonteOrcamentaria)\r\n" + //
                            "    WHERE ($idFonte IS NULL OR elementId(fonteExec) = $idFonte)\r\n" + //
                            "        AND ($exercicio IS NULL OR exec.anoExercicio = $exercicio)\r\n" + //
                            "    RETURN\r\n" + //
                            "        sum(vinculada_por.orcado) AS totalOrcado,\r\n" + //
                            "        sum(vinculada_por.autorizado) AS totalAutorizado, \r\n" + //
                            "        sum(REDUCE(total=0,e IN vinculada_por.empenhado | total + e ))  AS totalEmpenhado, \r\n" + //
                            "        sum(vinculada_por.dispSemReserva) AS totalDisponivel\r\n" + //
                            "}\r\n";

        String cypherQuery = cypherBase + 
                "RETURN\r\n" + //
                "    elementId(inv) AS id,\r\n" + //
                "    po.nome AS nome, \r\n" + //
                "    po.codigo AS codPO,\r\n" + //
                "    unidade.codigo + \" - \" + unidade.sigla AS unidadeOrcamentaria, \r\n" + //
                "    totalPrevisto,\r\n" + //
                "    totalContratado,\r\n" + //
                "    totalOrcado,\r\n" + //
                "    totalAutorizado, \r\n" + //
                "    totalEmpenhado, \r\n" + //
                "    totalDisponivel \r\n";

        String cypherCount = cypherBase + 
                "RETURN\r\n" + //
                "    COUNT(inv)\r\n";



        HashMap<String, Object> params = new HashMap<>();
        params.put("idPo", codPO);
        params.put("idUnidade", codUnidade);
        params.put("nome", nome);
        params.put("idFonte", idFonte);
        params.put("exercicio", exercicio);
        params.put("idPo", codPO);

        if(pageable != null) {
            cypherQuery += "SKIP $skip LIMIT $limit";
            params.put("skip", pageable.getOffset());
            params.put("limit", pageable.getPageSize());
        }

        List<TiraInvestimentoProjection> data = this.neo4jOperations.findAll(cypherQuery, params, TiraInvestimentoProjection.class);

        int count = (int) this.neo4jOperations.count(cypherCount, params);

        return new DataListResult<TiraInvestimentoProjection>(data, count);

    }

    public List<Investimento> findAllByFilterValores(
            String nome, String codUnidade, String codPO,
            Integer exercicio, String idFonte, Pageable pageable
        ) {

        // filtro em 2 etapas, primeiro filtra por investimento
        Investimento investimentoProbe = new Investimento();

        if(codPO != null) {
            PlanoOrcamentario planoProbe = new PlanoOrcamentario();
            planoProbe.setId(codPO);
            investimentoProbe.setPlanoOrcamentario(planoProbe);
        }

        if(codUnidade != null) {
            UnidadeOrcamentaria unidadeProbe = new UnidadeOrcamentaria();
            unidadeProbe.setId(codUnidade);
    
            investimentoProbe.setUnidadeOrcamentariaImplementadora(unidadeProbe);
        }

        List<Investimento> investimentosFiltrados;
        
        if(pageable == null) {
            investimentosFiltrados = repository.findBy(
                    Example.of(investimentoProbe), 
                    query -> query.all());
        } else {
            // existe algum bug bizarro que faz quebrar a query normal com
            // alguns UOs então é feita de forma mais indireta e informal
            investimentosFiltrados = repository.findByUoPo(codUnidade, codPO, pageable);
            investimentosFiltrados = repository.findAllById(investimentosFiltrados.stream().map(inv -> inv.getId()).toList());
        }


        /*
         * se utilizasse a API normal ele traria todos os custos do investimento que tem aquele custo, 
         * não é o que eu quero por isso eu filtro "manualmente"
         */

        for (Investimento investimento : investimentosFiltrados) {
            List<ExecucaoOrcamentaria> execsFiltrados = investimento.getExecucoesOrcamentaria();

            if(exercicio != null)
                execsFiltrados = execsFiltrados.stream()
                            .filter(exec -> exec.getAnoExercicio().equals(exercicio)).toList();

            for(ExecucaoOrcamentaria execucaoOrcamentaria : execsFiltrados) {
                
                Set<VinculadaPor> vinculadaFiltrada = execucaoOrcamentaria.getVinculadaPor();

                if(idFonte != null)
                    vinculadaFiltrada = vinculadaFiltrada.stream()
                                    .filter(vinculada -> vinculada.getFonteOrcamentaria().getId().equals(idFonte))
                                    .collect(Collectors.toSet());
                
                execucaoOrcamentaria.setVinculadaPor(new HashSet<>(vinculadaFiltrada));

            }

            investimento.setExecucoesOrcamentaria(execsFiltrados);
            
            
            for(Objeto objeto : objetoService.findObjetoByConta(investimento)) {
                List<Custo> custosFiltrados = objeto.getCustosEstimadores();

                if( exercicio != null)
                    custosFiltrados = custosFiltrados.stream()
                                .filter(custo -> custo.getAnoExercicio().equals(exercicio)).toList();

                for(Custo custo : custosFiltrados){
                    Set<IndicadaPor> indicadaFiltrado = custo.getIndicadaPor();

                    if(idFonte != null)
                        indicadaFiltrado = indicadaFiltrado.stream()
                                                    .filter(indicada -> indicada.getFonteOrcamentaria().getId().equals(idFonte))
                                                    .collect(Collectors.toSet());
                    
                    custo.setIndicadaPor(indicadaFiltrado);

                }

                objeto.setCustosEstimadores(new ArrayList<>(custosFiltrados));
            }
        }

        return investimentosFiltrados;

    }

    public String clean(String input) {
            String normalized = Normalizer.normalize(input, Normalizer.Form.NFD); 
            Pattern pattern = Pattern.compile("\\p{InCombiningDiacriticalMarks}+");
            return pattern.matcher(normalized).replaceAll("").toLowerCase();
    }

    public int ammountByFilterValores(
            String nome, String codUnidade, String codPO, Integer exercicio, String idFonte
        ){
        return this.findAllByFilterValores(nome, codUnidade, codPO, exercicio, idFonte, null).size();
    }

    public void addExecucao (String investimentoId, String execId) {

        this.repository.addExecucao(investimentoId, execId);

    }

    public Optional<Investimento> getByCodUoPo(String codUo, String codPo) {

        PlanoOrcamentario probePlano = new PlanoOrcamentario();
        probePlano.setCodigo(codPo);

        UnidadeOrcamentaria probeUnidade = new UnidadeOrcamentaria();
        probeUnidade.setCodigo(codUo);
        
        Investimento probeInvestimento = new Investimento();
        probeInvestimento.setPlanoOrcamentario(probePlano);
        probeInvestimento.setUnidadeOrcamentariaImplementadora(probeUnidade);


        return repository.findBy(Example.of(probeInvestimento), query -> query.first());
    }
}
