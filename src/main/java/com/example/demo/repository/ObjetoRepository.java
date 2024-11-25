package com.example.demo.repository;

import com.example.demo.model.DataMock;
import com.example.demo.model.Objeto;

//public interface ObjetoRepository extends Neo4jRepository<Objeto, String> {
public class ObjetoRepository {//extends Neo4jRepository<Objeto, String> {

    public Objeto save(Objeto objeto) {
        // TODO Auto-generated method stub
        objeto.setId(String.valueOf(DataMock.noObjetos.size()));
        DataMock.noObjetos.add(objeto);
        return objeto;
    }
    
}
