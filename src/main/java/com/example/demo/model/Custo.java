package com.example.demo.model;

import java.io.Serializable;
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
public class Custo extends Entidade implements Serializable {
     
    private double previsto;
    private double contratado;

    @Relationship(type = "EM", direction = Direction.OUTGOING)
    private Ano anoExercicio;

    @Relationship(type = "ESTIMADO", direction = Direction.OUTGOING)
    private Objeto objetoEstimado;

    @Relationship(type = "INDICADA", direction = Direction.INCOMING)
    private FonteOrcamentaria fonteOrcamentariaIndicadora;

    public Custo(String anoExercicio, double previsto, double contratado, Objeto objetoEstimado) {
        this.anoExercicio = new Ano(anoExercicio);
        this.previsto = previsto;
        this.contratado = contratado;
        this.setObjetoEstimado(objetoEstimado);
    }
    
    public Custo(Ano anoExercicio, Objeto objetoEstimado) {
        this.anoExercicio = anoExercicio;
        this.objetoEstimado = objetoEstimado;
    }
    
    
    public Custo(Ano anoExercicio, Objeto objetoEstimado, double previsto, double contratado, FonteOrcamentaria fonte) {
        this(anoExercicio, objetoEstimado);
        this.setValores(previsto, contratado, fonte);
    }

    public void setValores(double previsto, double contratado, FonteOrcamentaria fonte){
        this.previsto = previsto;
        this.contratado = contratado;
        this.fonteOrcamentariaIndicadora = fonte;
    }

    public static Custo criar(String ano, Objeto objeto){
        Custo novo = new Custo();
        novo.anoExercicio = new Ano(ano);
        novo.objetoEstimado = objeto;
        DataMock.noCustos.add(novo);
        return novo;
    }

    public static Custo findOrCreate(String ano, Objeto objeto){
        List<Custo> result = DataMock.noCustos.stream()
            .filter(custo ->  {
                return custo.anoExercicio.getAno().equals(ano)
                    && custo.objetoEstimado.equals(objeto);
            }).toList();

        if(result.isEmpty()) {
            return Custo.criar(ano, objeto);
        } else {
            return result.get(0);
        }
    }

    public static Custo findOrCreate(String ano, Custo custoBase){
        return findOrCreate(ano, custoBase.objetoEstimado);
    }

}
