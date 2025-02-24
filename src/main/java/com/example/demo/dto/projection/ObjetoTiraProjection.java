package com.example.demo.dto.projection;

import java.util.ArrayList;

import com.example.demo.model.Conta;
import com.example.demo.model.Custo;
import com.example.demo.model.EmStatus;

public interface ObjetoTiraProjection {
    public String getId();
    public String getNome();
    public String getTipo();
    public EmStatus getEmStatus();
    public EmEtapaProjection getEmEtapa();
    public ArrayList<Custo> getCustosEstimadores();
    public Conta getConta();

}
