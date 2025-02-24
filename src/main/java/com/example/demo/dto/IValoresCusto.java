package com.example.demo.dto;

import java.util.Set;

import org.springframework.data.neo4j.core.schema.RelationshipId;

import com.example.demo.dto.projection.IValoresIndicadaPor;

import lombok.Data;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

public interface IValoresCusto {
    Set<IValoresIndicadaPor> getIndicadaPor(); 
}