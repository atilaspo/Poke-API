package com.meli.desafiospring.ServiceTest;

import com.meli.desafiospring.Exceptions.OutOfOptions;
import com.meli.desafiospring.Models.ProductDTO;
import com.meli.desafiospring.Models.ResponseDTO;
import com.meli.desafiospring.Repository.IProductRepository;
import com.meli.desafiospring.Repository.ProductRepository;
import com.meli.desafiospring.Service.IProductService;
import com.meli.desafiospring.Service.ProductService;
import org.apache.coyote.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.openMocks;


public class ServiceTest {

    // Mockea el repo
    @Mock
    IProductRepository productRepository;

    @InjectMocks
    IProductService productService = new ProductService();

    // Crea los mocks antes de cada Test
    @BeforeEach
    public void beforeEach() {
        openMocks(this);
    }

    @Test
    public void TestServiceFilter() throws OutOfOptions {
        List<ProductDTO> products = new ArrayList<>();
        products.add(new ProductDTO(1, "martillo", "Herramientas", "ACME", 420, 1, true, 5));
        products.add(new ProductDTO(5, "pinzas", "Herramientas", "sorny", 234, 2, false, 2));
        products.add(new ProductDTO(2, "moladora", "Herramientas", "thilips", 1420, 1, true, 3));
        products.add(new ProductDTO(8, "asdsd", "Herramientas", "tuvieja", 500, 3, false, 5));


        ResponseDTO respuesta = new ResponseDTO();
        respuesta.setProducts(products);

        when(productRepository.getProducts()).thenReturn(products);


        ResponseDTO actual = productService.filterProducts(null, "Herramientas", null,null,null,null, null, null);
        Assertions.assertIterableEquals(respuesta.getProducts(), actual.getProducts());


    }
}
