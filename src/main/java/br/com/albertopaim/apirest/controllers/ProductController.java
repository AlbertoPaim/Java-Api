package br.com.albertopaim.apirest.controllers;

import br.com.albertopaim.apirest.Utils.HandlerResponse;
import br.com.albertopaim.apirest.models.Product;
import br.com.albertopaim.apirest.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/products")
public class ProductController {
    @Autowired
    private ProductRepository productRepository;

    @GetMapping()
    public List<Product> getProduct() {
        return productRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> obterProduto(@PathVariable Integer id) {
        Optional<Product> produto = productRepository.findById(id);

        if(!(produto.isPresent())){
            return HandlerResponse.generateResponse("Produto não encontrado", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Object>(produto.get(), HttpStatus.OK);
    }


}
