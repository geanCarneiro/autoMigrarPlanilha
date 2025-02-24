package com.example.demo.model;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.RelationshipProperties;
import org.springframework.data.neo4j.core.schema.TargetNode;

import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@RelationshipProperties
public class VinculadaPor extends Entidade {

    @TargetNode
    private FonteOrcamentaria fonteOrcamentaria;

    private double autorizado;
    private double dispSemReserva;
    private double[] empenhado = new double[12];
    private double[] liquidado = new double[12];
    private double[] pago = new double[12];
    private double orcado;

    



}
