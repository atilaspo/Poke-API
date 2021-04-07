package com.meli.desafiospring.Models;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class PurchaseResponse {
    private Double id;
    public List<ProductDTO> products = new ArrayList<>();
    private Double total;
    private HttpStatus httpStatus;
    private String message;
}
