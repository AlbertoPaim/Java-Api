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

        if (!(produto.isPresent())) {
            return HandlerResponse.generateResponse("Produto não encontrado", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Object>(produto.get(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Object> criarProduto(@RequestBody Product product) {
        if (product.getName() == null) {
            return HandlerResponse.generateResponse("Nome do produto obrigatório", HttpStatus.BAD_REQUEST);
        }

        if (product.getPrice() == null) {
            return HandlerResponse.generateResponse("Preço do produto obrigatório", HttpStatus.BAD_REQUEST);
        }

        Product newProduct = productRepository.save(product);
        return new ResponseEntity<Object>(newProduct, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateProduct(@PathVariable Integer id, @RequestBody Product product) {

        Optional<Product> oldProduto = productRepository.findById(id);

        if (!(oldProduto.isPresent())) {
            return HandlerResponse.generateResponse("Produto não encontrado", HttpStatus.NOT_FOUND);
        }

        if (product.getName() == null) {
            return HandlerResponse.generateResponse("Nome do produto obrigatório", HttpStatus.BAD_REQUEST);
        }

        if (product.getPrice() == null) {
            return HandlerResponse.generateResponse("Preço do produto obrigatório", HttpStatus.BAD_REQUEST);
        }

        Product updateProduct = oldProduto.get();

        updateProduct.setName(product.getName());
        updateProduct.setPrice(product.getPrice());
        updateProduct.setDescription(product.getDescription());

        productRepository.save(updateProduct);

        return ResponseEntity.noContent().build();

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteProduct(@PathVariable Integer id) {
        Optional<Product> oldProduct = productRepository.findById(id);
        if (!(oldProduct.isPresent())) {
            return HandlerResponse.generateResponse("Produto não encontrado", HttpStatus.NOT_FOUND);
        }

        productRepository.delete(oldProduct.get());
        return ResponseEntity.noContent().build();
    }

}
