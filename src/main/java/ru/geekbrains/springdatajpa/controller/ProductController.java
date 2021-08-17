package ru.geekbrains.springdatajpa.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.geekbrains.springdatajpa.dto.ProductDto;
import ru.geekbrains.springdatajpa.service.ProductService;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @GetMapping("/products")
    public List<ProductDto> findAll() {
        return productService.findAll();
    }

    @GetMapping(value = "/products/filter", params = "max_price")
    public List<ProductDto> findAllByMaxPrice(@RequestParam(name = "max_price") int maxPrice) {
        return productService.findAllByMaxPrice(maxPrice);
    }

    @GetMapping(value = "/products/filter", params = "min_price")
    public List<ProductDto> findAllByMinPrice(@RequestParam(name = "min_price") int minPrice) {
        return productService.findAllByMinPrice(minPrice);
    }

    @GetMapping(value = "/products/filter", params = {"max_price", "min_price"})
    public List<ProductDto> findAllByPriceBetween(@RequestParam(name = "min_price") int minPrice, @RequestParam(name = "max_price") int maxPrice) {
        return productService.findAllByPriceBetween(minPrice, maxPrice);
    }

    @GetMapping("/products/{id}")
    public ProductDto findById(@PathVariable Long id) {
        return productService.findById(id);
    }

    @PostMapping("/products")
    public ProductDto saveProduct(@RequestBody ProductDto productDto) {
        return productService.save(productDto);
    }

    @GetMapping("/products/delete/{id}")
    public String removeProduct(@PathVariable Long id) {
        productService.delete(id);
        return "redirect:/products";
    }
}
