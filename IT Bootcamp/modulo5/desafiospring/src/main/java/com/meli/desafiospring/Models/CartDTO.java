package com.meli.desafiospring.Models;

import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;
import java.util.List;

@Getter
@Setter
public class Cart {
    private HashMap<Integer, List<ProductDTO>> ShoppingCartList;
}
