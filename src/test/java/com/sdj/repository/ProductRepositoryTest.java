package com.sdj.repository;

import com.sdj.entity.Product;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Sort;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ProductRepositoryTest {

    @Autowired
    private ProductRepository productRepository;

    // save

    @Test
    void save() {
        // Create Product
        Product product = new Product();
        product.setName("Samsung");
        product.setDescription("Best budget phones");
        product.setPrice(new BigDecimal(23500));
        product.setActive(true);
        product.setSku("M32");
        product.setImageUrl("samsung123");

        // Save Product
        productRepository.save(product);

        // Display product info
        System.out.println(product.getId());
        System.out.println(product.toString());

    }


    // update
    @Test
    public void updateWithSave() {
        Product p = productRepository.findById(1L).get();

        p.setName("MI NOTE 5");

        productRepository.save(p);
        System.out.println(p.getId());
        System.out.println(p.toString());

    }

    // saveAll
    @Test
    public void saveAllProducts() {
        Product p1 = new Product();
        p1.setName("Vivo");
        p1.setDescription("Best budget phones");
        p1.setPrice(new BigDecimal(25000));
        p1.setActive(true);
        p1.setSku("vivo");
        p1.setImageUrl("vivo.com");

        Product p2 = new Product();
        p2.setName("Oppo");
        p2.setDescription("Best budget phones");
        p2.setPrice(new BigDecimal(22000));
        p2.setActive(true);
        p2.setSku("oppo");
        p2.setImageUrl("oppo.com");

        List<Product> products = productRepository.saveAll(List.of(p1, p2));

        System.out.println(products);

    }

    // findById
    @Test
    public void findByIdMethod() {
        Product product = productRepository.findById(2L).get();
        System.out.println(product);

    }

    // findAll()
    @Test
    public void findAllMethod() {
        List<Product> products = productRepository.findAll();

        products.forEach(
                p -> System.out.println(p.getName())
        );
    }

    // findAllById()
    @Test
    public void findAllByIdsMethod() {
        List<Product> products = productRepository.findAllById(List.of(1L, 2L));
        products.forEach(
                p -> System.out.println(p.getName())
        );

    }

    // deleteById()
    @Test
    public void deleteByIdMethod() {
        productRepository.deleteById(3L);
    }

    // count()
    @Test
    public void countMethod() {
        long productsCount = productRepository.count();
        System.out.println(productsCount);
    }

    // existById()
    @Test
    public void existByIdMethod() {
        Boolean isExist = productRepository.existsById(2L);
        System.out.println(isExist);
    }

    @Test
    public void findByPriceBetweenMethod() {
        List<Product> products = productRepository.findByPriceBetween(new BigDecimal(12000), new BigDecimal(25000));

        products.forEach((p) -> {
            System.out.println(p.getName() + " " + p.getPrice());
        });

    }

    @Test
    public void findByDateCreatedMethod() {
        List<Product> products = productRepository.findByDateCreatedBetween(LocalDateTime.of(2025, 9, 11, 16, 58, 13), LocalDateTime.of(2025, 9, 11, 22, 0, 0));

        products.forEach((p) -> {
            System.out.println(p.getName());
        });
    }


    @Test
    public void sorting() {
        String sortBy = "price";
        String sortDir = "desc";

        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();

        List<Product> products = productRepository.findAll(sort);

        products.forEach((p) -> {
            System.out.println(p);
        });

    }
}
