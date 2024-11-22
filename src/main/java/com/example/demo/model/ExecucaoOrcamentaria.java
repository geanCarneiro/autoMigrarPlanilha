package com.example.demo.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Relationship;
import org.springframework.data.neo4j.core.schema.Relationship.Direction;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Node
public class ExecucaoOrcamentaria extends Entidade implements Serializable {
    
    private double orcamento;
    private double autorizado;
    private double[] liquidado;

    @Relationship(type = "EM", direction = Direction.OUTGOING)
    private Ano anoExercicio;

    @Relationship(type = "DELIMITA", direction = Direction.OUTGOING)
    private Conta contaDelimitada;

    @Relationship(type = "VINCULA", direction = Direction.INCOMING)
    private FonteOrcamentaria fonteOrcamentariaVinculadora;
    
    public ExecucaoOrcamentaria(Ano ano, Conta contaDelimitada, double orcamento, double autorizado, FonteOrcamentaria fonte) {
        this.anoExercicio = ano;
        this.orcamento = orcamento;
        this.autorizado = autorizado;
        this.liquidado = new double[12];
        this.contaDelimitada = contaDelimitada;
        this.fonteOrcamentariaVinculadora = fonte;
    }
    
    public ExecucaoOrcamentaria(Ano ano, Conta contaDelimitada, FonteOrcamentaria fonte) {
        this.anoExercicio = ano;
        this.contaDelimitada = contaDelimitada;
        this.fonteOrcamentariaVinculadora = fonte;
    }

    public ExecucaoOrcamentaria(String ano, double orcamento, double autorizado, double[] liquidado, Conta contaDelimitada) {
        this.anoExercicio = new Ano(ano);
        this.orcamento = orcamento;
        this.autorizado = autorizado;
        this.liquidado = liquidado == null ? new double[12] : liquidado;
        this.setContaDelimitada(contaDelimitada);
    }

    public ExecucaoOrcamentaria(String ano, PlanoOrcamentario po, UnidadeOrcamentaria uo, Conta conta) {
        this.anoExercicio = new Ano(ano);
        this.orcamento = 0d;
        this.autorizado = 0d;
        this.liquidado = new double[12];
        this.contaDelimitada = conta;
    }

    public void setValores(double orcamento, double autorizado, double[] liquidado, FonteOrcamentaria fonte){
        this.orcamento = orcamento;
        this.autorizado = autorizado;
        this.liquidado = liquidado == null ? new double[12] : liquidado;
        this.fonteOrcamentariaVinculadora = fonte;
    }

    public static List<ExecucaoOrcamentaria> gerarExecucoes(PlanoOrcamentario po, Conta conta){
        ArrayList<ExecucaoOrcamentaria> execucoes = new ArrayList<>();
        String ano = String.valueOf(LocalDate.now().getYear()+1);

        
        return execucoes;
    }

    public static ExecucaoOrcamentaria criar(String ano, PlanoOrcamentario po, UnidadeOrcamentaria uo, Conta conta){
        ExecucaoOrcamentaria novo = new ExecucaoOrcamentaria(ano, po, uo, conta);
        ArrayList<ExecucaoOrcamentaria> execucoes;
        if(conta.getExecucoesOrcamentariaDelimitadores() == null)
            execucoes = new ArrayList<>();
        else
            execucoes = new ArrayList<>(conta.getExecucoesOrcamentariaDelimitadores());
        
        execucoes.add(novo);
        
        conta.setExecucoesOrcamentariaDelimitadores(execucoes);
        
        DataMock.noExecucaoOrcamentarias.add(novo);
        return novo;
    }

    public static ExecucaoOrcamentaria findOrCreate(String ano, PlanoOrcamentario po, UnidadeOrcamentaria uo, Conta conta){
        List<ExecucaoOrcamentaria> result = DataMock.noExecucaoOrcamentarias.stream()
            .filter(exec -> {
                return exec.anoExercicio.getAno().equals(ano)
                    && exec.contaDelimitada.equals(conta);
            }).toList();
        
        if(result.isEmpty()) {
            ExecucaoOrcamentaria nova = criar(ano, po, uo, conta);
            ((ArrayList<ExecucaoOrcamentaria>) conta.getExecucoesOrcamentariaDelimitadores()).add(nova);
            return nova;
        } else {
            return result.get(0);
        }
    }

}
