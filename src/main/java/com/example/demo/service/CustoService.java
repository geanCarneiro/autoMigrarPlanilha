package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dto.ValoresCusto;
import com.example.demo.model.Custo;
import com.example.demo.model.IndicadaPor;
import com.example.demo.model.Objeto;
import com.example.demo.repository.CustoRepository;


@Service
public class CustoService {
    
    @Autowired
    private CustoRepository repository;

    private ObjetoService objetoService;


    public void saveAll(List<Custo> custos) {
        repository.saveAll(custos);
    }

    public List<Custo> getAllByExercicio(String exercicio){
        return repository.findByExercicio(exercicio);
    }

    public ValoresCusto getValoresTotais(String nome, String idFonte, Integer exercicio, String idUnidade, String idPlano){
       
        List<Objeto> objetosPorFiltro = objetoService.getAllListByFilter(exercicio, nome, idUnidade, idPlano, null, null, null);

        objetosPorFiltro = objetosPorFiltro.stream().filter(obj -> obj.getEmEtapa() == null).toList();
        
        Double totalPrevisto = 0d;
        Double totalContratado = 0d;

        for(Objeto objeto : objetosPorFiltro){

            for(Custo custo : objeto.getCustosEstimadores()){

                for(IndicadaPor indicadaPor: custo.getIndicadaPor()){

                    totalPrevisto += indicadaPor.getPrevisto();
                    totalContratado += indicadaPor.getContratado();  
                }

            }

        }

        return new ValoresCusto(totalPrevisto, totalContratado);
    }

    @Autowired
    public void setObjetoService(ObjetoService objetoService) {
        this.objetoService = objetoService;
    }

    


}