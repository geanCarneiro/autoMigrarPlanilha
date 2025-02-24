package com.example.demo.dto;

import java.io.Serializable;
import java.util.Set;
import java.util.stream.Collectors;

import com.example.demo.model.Custo;

import lombok.Getter;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CustoDTO implements Serializable {
    
    private String id;
    private Integer anoExercicio;

    private Set<IndicadaPorDto> indicadaPor;

    public CustoDTO(Custo custo) {
        this.id = custo.getId();
        this.anoExercicio = custo.getAnoExercicio();
        

        this.indicadaPor = custo.getIndicadaPor().stream()
                    .map(indicadaPor -> new IndicadaPorDto(indicadaPor))
                    .sorted((ip1, ip2) -> ip1.fonteOrcamentaria().getCodigo().compareTo(ip2.fonteOrcamentaria().getCodigo()))
                    .collect(Collectors.toSet());

    }

}
