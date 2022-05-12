package mk.ukim.finki.service;

import mk.ukim.finki.models.Category;
import mk.ukim.finki.models.Manufacturer;
import mk.ukim.finki.models.Product;

import java.util.List;
import java.util.Optional;

public interface ProductService {

    List<Product> findAll();
    Optional<Product> findById(Long id);
    Optional<Product> save(String name, Double price, Integer quantity, Long categoryId, Long manufacturerId);
    void deleteById(Long id);
}
