package ru.geekbrains.springdatajpa.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.geekbrains.springdatajpa.model.Product;
import ru.geekbrains.springdatajpa.service.ProductService;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @GetMapping("/products")
    public List<Product> findAll() {
        return productService.findAll();
    }

    @GetMapping(value = "/products/filter", params = "max_price")
    public List<Product> findAllByMaxPrice(@RequestParam(name = "max_price") int maxPrice) {
        return productService.findAllByMaxPrice(maxPrice);
    }

    @GetMapping(value = "/products/filter", params = "min_price")
    public List<Product> findAllByMinPrice(@RequestParam(name = "min_price") int minPrice) {
        return productService.findAllByMinPrice(minPrice);
    }

    @GetMapping(value = "/products/filter", params = {"max_price", "min_price"})
    public List<Product> findAllByPriceBetween(@RequestParam(name = "min_price") int minPrice, @RequestParam(name = "max_price") int maxPrice) {
        return productService.findAllByPriceBetween(minPrice, maxPrice);
    }

    @GetMapping("/products/{id}")
    public Product findById(@PathVariable Long id) {
        return productService.findById(id).orElseGet(() -> new Product("wrong ID", 0));
    }

    @PostMapping("/products")
    public String saveProduct(@RequestParam String title, @RequestParam int price) {
        Product product = new Product(title, price);
        productService.save(product);
        return "redirect:/products";
    }

    @GetMapping("/products/delete/{id}")
    public String removeProduct(@PathVariable Long id) {
        productService.delete(id);
        return "redirect:/products";
    }
}
