package com.learnjava.arifudinJSB.services;

import com.learnjava.arifudinJSB.models.Product;
import com.learnjava.arifudinJSB.repositorys.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
@Transactional
public class ProductService {

    private final ProductRepository productRepository;

    // Read (GET All with Pagination)
    public Page<Product> findAllProducts(int page, int size, String sortDirection, String sortBy, String name) {
//        Sort sort = Sort.by(Sort.Direction.fromString(sortDirection), sortBy);
//        PageRequest pageRequest = PageRequest.of(page, size, sort);
//        return productRepository.findAllWithFilter(name, pageRequest);

        // ✅ Validasi sortDirection
        Sort.Direction direction;
        if (sortDirection == null || sortDirection.trim().isEmpty() ||
                (!sortDirection.equalsIgnoreCase("asc") && !sortDirection.equalsIgnoreCase("desc"))) {
            direction = Sort.Direction.ASC; // Default ASC jika null/empty/invalid
        } else {
            direction = Sort.Direction.fromString(sortDirection);
        }

        // ✅ Default sortBy jika null atau kosong
        if (sortBy == null || sortBy.trim().isEmpty()) {
            sortBy = "id"; // Default sortBy = "id"
        }

        Pageable pageable = PageRequest.of(page, size, Sort.by(direction, sortBy));
        return productRepository.findAllWithFilter(name, pageable);
    }

    // Read (GET Single Product)
    public Product findById(Long id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Product not found with id: " + id));
    }

    // Create
    public Product createProduct(Product product) {
        return productRepository.save(product);
    }

    // Update
    public Product updateProduct(Long id, Product productDetails) {
        Product existingProduct = findById(id);

        existingProduct.setName(productDetails.getName());
        existingProduct.setPrice(productDetails.getPrice());
        existingProduct.setDescription(productDetails.getDescription());

        return productRepository.save(existingProduct);
    }

    // Delete
    public void deleteProduct(Long id) {
        Product product = findById(id);
        productRepository.delete(product);
    }
}