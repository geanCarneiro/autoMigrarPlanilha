package com.example.demo.dto;

import java.time.ZonedDateTime;

import com.example.demo.model.EmStatus;
import com.example.demo.utils.DateTimeUtils;


public record EmStatusDTO(
    String id,
    StatusDTO status,
    String timestamp    
) {
    public static EmStatusDTO parse (EmStatus model) {
        return model == null ? null
        : new EmStatusDTO(
            model.getId(),
            StatusDTO.parse(model.getStatus()), 
            DateTimeUtils.formatZonedDateTime(model.getTimestamp()) 
        );
    }
}
