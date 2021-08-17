package ru.geekbrains.springdatajpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.geekbrains.springdatajpa.model.Product;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    List<Product> findAllByPriceLessThanEqual(int maxPrice);

    List<Product> findAllByPriceGreaterThanEqual(int minPrice);

    List<Product> findAllByPriceBetween(int minPrice, int maxPrice);
}
