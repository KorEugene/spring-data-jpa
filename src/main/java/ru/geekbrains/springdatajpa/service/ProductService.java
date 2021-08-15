package ru.geekbrains.springdatajpa.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.geekbrains.springdatajpa.dto.ProductDto;
import ru.geekbrains.springdatajpa.model.Product;
import ru.geekbrains.springdatajpa.repository.ProductRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;

    public ProductDto findById(Long id) {
        return new ProductDto(productRepository.findById(id).orElseGet(() -> new Product("wrong ID", 0)));
    }

    public List<ProductDto> findAll() {
        return productRepository.findAll().stream()
                .map(ProductDto::new)
                .collect(Collectors.toList());
    }

    public List<ProductDto> findAllByMaxPrice(int maxPrice) {
        return productRepository.findAllByPriceLessThanEqual(maxPrice).stream()
                .map(ProductDto::new)
                .collect(Collectors.toList());
    }

    public List<ProductDto> findAllByMinPrice(int minPrice) {
        return productRepository.findAllByPriceGreaterThanEqual(minPrice).stream()
                .map(ProductDto::new)
                .collect(Collectors.toList());
    }

    public List<ProductDto> findAllByPriceBetween(int minPrice, int maxPrice) {
        return productRepository.findAllByPriceBetween(minPrice, maxPrice).stream()
                .map(ProductDto::new)
                .collect(Collectors.toList());
    }

    public Product save(Product product) {
        return productRepository.save(product);
    }

    public void delete(Long id) {
        productRepository.deleteById(id);
    }
}
