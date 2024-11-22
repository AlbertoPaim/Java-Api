package br.com.albertopaim.apirest.repositories;

import br.com.albertopaim.apirest.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Integer> {

}
