package br.com.albertopaim.apirest.controllers;

import br.com.albertopaim.apirest.models.Product;
import br.com.albertopaim.apirest.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {
    @Autowired
    private ProductRepository productRepository;

    @GetMapping()
    public List<Product> getProduct() {
        return productRepository.findAll();
    }

}
