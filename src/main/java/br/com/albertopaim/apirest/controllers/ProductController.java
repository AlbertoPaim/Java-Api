package br.com.albertopaim.apirest.controllers;

import br.com.albertopaim.apirest.Utils.HandlerResponse;
import br.com.albertopaim.apirest.models.Product;
import br.com.albertopaim.apirest.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping
    public ResponseEntity<Object> criarProduto(@RequestBody Product product){
        if(product.getName() == null){
            return HandlerResponse.generateResponse("Nome do produto obrigatório", HttpStatus.BAD_REQUEST);
        }

        if(product.getPrice() == null){
            return HandlerResponse.generateResponse("Preço do produto obrigatório", HttpStatus.BAD_REQUEST);
        }

        Product newProduct = productRepository.save(product);
        return new ResponseEntity<Object>(newProduct, HttpStatus.CREATED);
    }

}
