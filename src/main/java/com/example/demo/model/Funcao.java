package com.example.demo.model;

import java.util.Set;

import org.springframework.data.neo4j.core.schema.Node;

import com.example.demo.dto.FuncaoDTO;

import lombok.Getter;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Node
public class Funcao extends Entidade {
    
    private String nome;


    public Funcao(FuncaoDTO dto) {
        this.setId(dto.getId());
        this.nome = dto.getNome();
    }

    public static boolean testarFuncao(Set<Funcao> funcoes, String targetFuncao){
        
        for(Funcao funcao : funcoes){
            if(funcao.getNome().equals(targetFuncao)) return true;
        }

        return false;
    }

}
