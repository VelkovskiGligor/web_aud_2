package mk.ukim.finki.service.impl;

import mk.ukim.finki.models.Category;
import mk.ukim.finki.models.Manufacturer;
import mk.ukim.finki.models.Product;
import mk.ukim.finki.models.exeptions.CategoryNotFoundException;
import mk.ukim.finki.models.exeptions.ManufacturerNotFoundException;

import mk.ukim.finki.repository.jpa.CategoryRepository;
import mk.ukim.finki.repository.jpa.ManufacturerRepository;
import mk.ukim.finki.repository.jpa.ProductRepository;
import mk.ukim.finki.service.ProductService;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;
    private final ManufacturerRepository manufacturerRepository;

    public ProductServiceImpl(ProductRepository productRepository, CategoryRepository categoryRepository, ManufacturerRepository manufacturerRepository) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
        this.manufacturerRepository = manufacturerRepository;
    }

    @Override
    public List<Product> findAll() {
        return productRepository.findAll();
    }

    @Override
    public Optional<Product> findById(Long id) {

        return productRepository.findById(id);
    }

    public Optional<Product> findByName(String name) {

        return productRepository.findByName(name);
    }
    @Transactional
    @Override
    public Optional<Product> save(String name, Double price, Integer quantity, Long categoryId, Long manufacturerId) {
        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new CategoryNotFoundException(categoryId));
        Manufacturer manufacturer = manufacturerRepository.findById(manufacturerId)
                .orElseThrow(() -> new ManufacturerNotFoundException(manufacturerId));
        this.productRepository.deleteByName(name);
        return Optional.of( this.productRepository.save(new Product(name, price, quantity, category, manufacturer)));
    }

    @Override
    public void deleteById(Long id) {
        this.productRepository.deleteById(id);
    }
}