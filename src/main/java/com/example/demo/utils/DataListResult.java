package com.example.demo.utils;

import java.util.List;

public record DataListResult<T>(
    List<T> data,
    int ammount
) {
    
}
