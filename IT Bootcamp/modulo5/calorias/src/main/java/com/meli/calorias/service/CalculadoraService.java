package com.meli.calorias.service;

import com.meli.calorias.DTOS.PlatoDTO;
import com.meli.calorias.DTOS.ResponseDTO;
import org.springframework.stereotype.Service;


public interface CalculadoraService {
    ResponseDTO calculate(PlatoDTO plato);
}
