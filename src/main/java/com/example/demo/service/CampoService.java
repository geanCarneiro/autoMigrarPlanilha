package com.example.demo.service;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.example.demo.model.Campo;
import com.example.demo.repository.CampoRepository;


@Service
public class CampoService {
    
    @Autowired
    private CampoRepository campoRepository;

    public List<Campo> findAll(){
 
        return campoRepository.findAll(Sort.by("campoId"));
    }

    public void gerarCampos() {

        List<Campo> campos = Arrays.asList(
            new Campo("objetoTipoDespesa", "Tipo da Despesa"),
            new Campo("objetoTipoObjeto", "Tipo de Objeto"),
            new Campo("objetoNome", "Nome"),
            new Campo("objetoDescricao", "Descrição"),
            new Campo("objetoUnidadeOrcamentaria", "Unidade Orçamentária"),
            new Campo("objetoPlanoOrcamentario", "Plano Orçamentário"),
            new Campo("objetoMicrorregiaoAtendida", "Microrregião Atendida"),
            new Campo("objetoAreaTematica", "Área Temática"),
            new Campo("objetoInformacoesComplementares", "Informações Complementares"),
            new Campo("objetoContrato", "Contrato"),
            new Campo("objetoTiposPlano", "Tipos de Plano(s)"),
            new Campo("objetoValores", "Valores")
        );

        campoRepository.saveAll(campos);

    }


}
