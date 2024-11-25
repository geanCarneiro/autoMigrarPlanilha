package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Custo;
import com.example.demo.repository.CustoRepository;
import com.example.demo.repository.CustoRepositoryReal;

@Service
public class CustoService {
    
    private CustoRepository repository = new CustoRepository();

    @Autowired
    private CustoRepositoryReal repReal;

    public void saveAll(List<Custo> custos) {
        repository.saveAll(custos);
    }

    public Custo saveReal(Custo custos){
        return repReal.save(custos);
    }

    
    public Custo save(Custo custos) {
        return repository.save(custos);
    }

    public Custo findOrCreate(Custo _custo) {
        Optional<Custo> result = repository.findByFilter(_custo.getAnoExercicio(), _custo.getObjetoEstimado(), _custo.getFonteOrcamentariaIndicadora());

        Custo custo;
        if(result.isPresent()) {
            custo = result.get();

            custo.setPrevisto(_custo.getPrevisto());
            custo.setContratado(_custo.getContratado());
            
        } else {
            custo = _custo;
        }
        return repository.save(custo);
    }
}
