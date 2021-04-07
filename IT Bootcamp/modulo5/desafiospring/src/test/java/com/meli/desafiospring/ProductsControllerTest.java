package com.meli.desafiospring.ControllerTest;
import com.meli.desafiospring.Controller.ProductsController;
import com.meli.desafiospring.Exceptions.OutOfOptions;
import com.meli.desafiospring.Models.ProductDTO;
import com.meli.desafiospring.Models.ResponseDTO;
import com.meli.desafiospring.Service.IProductService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.MockitoAnnotations.openMocks;


/*
    public class TestArticlesController {

    private ArticlesController articlesController;

    @Mock
    private ArticlesService articlesService;

    @BeforeEach
    public void beforeEach() {
        openMocks(this);
        this.articlesController = new ArticlesController(articlesService);
    }
    @Test
    public void testGetArticles() throws APIException {
        List<Article> articles = new ArrayList<>();
        articles.add(new Article(1, "name", "category", "brand", 100.0, 3, 4.0, true));
        articles.add(new Article(2, "name", "category", "brand", 100.0, 3, 4.0, true));
        articles.add(new Article(3, "name", "category", "brand", 100.0, 3, 4.0, true));
        articles.add(new Article(4, "name", "category", "brand", 100.0, 3, 4.0, true));
        Mockito.when(articlesService.getArticles(new HashMap<>())).thenReturn(articles);
        Assertions.assertIterableEquals(articles, this.articlesController.getArticles(new HashMap<>()));
    }
}
 */

/*
public class ProductsControllerTest {

    // Inyecta el controller
    @InjectMocks
    private ProductsController controller;

    // Mockea el service
    @Mock
    private IProductService productService;

    // Crea los mocks antes de cada Test
    @BeforeEach
    public void beforeEach() {
        openMocks(this);
    }


    // Test en si
    @Test
    public void testShowProducts() throws OutOfOptions {
        List<ProductDTO> products = new ArrayList<>();
        products.add(new ProductDTO(1, "Jotita", "Herramientas", "ACME", 420, 1, true, 5));
        products.add(new ProductDTO(5, "picador", "Herramientas", "sorny", 234, 2, false, 2));
        products.add(new ProductDTO(2, "florcitas", "Herramientas", "thilips", 1420, 1, true, 3));
        products.add(new ProductDTO(8, "bong", "zapatillas", "tuvieja", 500, 3, false, 5));

        // Genero un Response porque es lo que devuelve mi Controller
        ResponseDTO expected = new ResponseDTO();
        expected.setProducts(products);


        Mockito.when(productService.filterProducts(null,null, null, null, null, null, null, null)).thenReturn(expected);
        var actual = controller.showProducts(null, null, null, null, null, null, null, null);
        Assertions.assertIterableEquals(expected, actual);
    }
}
*/

