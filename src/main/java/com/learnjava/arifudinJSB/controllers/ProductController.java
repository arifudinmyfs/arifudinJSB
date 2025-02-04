package com.learnjava.arifudinJSB.controllers;

import com.learnjava.arifudinJSB.models.Product;
import com.learnjava.arifudinJSB.services.ProductService;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/products")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;

    // GET ALL dengan Pagination (sudah ada)
    @GetMapping
    public ResponseEntity<?> getAllProducts(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "asc") String sortDirection,
            @RequestParam(defaultValue = "id") String sortBy,
            @RequestParam(required = false) String name
    ) {
        Page<Product> result = productService.findAllProducts(
                page, size, sortDirection, sortBy, name
        );

        DataResponse response = new DataResponse();
        response.data = new ResponseData(
                size,
                page,
                result.getContent()
        );

        return ResponseEntity.ok(response);
    }

    // GET BY ID
    @GetMapping("/{id}")
    public ResponseEntity<DataResponse> getProductById(@PathVariable Long id) {
        Product product = productService.findById(id);
        return ResponseEntity.ok(
                new DataResponse(new ResponseData(1, 0, product))
        );
    }

    // CREATE
    @PostMapping
    public ResponseEntity<DataResponse> createProduct(@RequestBody Product product) {
        Product savedProduct = productService.createProduct(product);
        return new ResponseEntity<>(
                new DataResponse(new ResponseData(1, 0, savedProduct)),
                HttpStatus.CREATED
        );
    }

    // UPDATE
    @PutMapping("/{id}")
    public ResponseEntity<DataResponse> updateProduct(
            @PathVariable Long id,
            @RequestBody Product product
    ) {
        Product updatedProduct = productService.updateProduct(id, product);
        return ResponseEntity.ok(
                new DataResponse(new ResponseData(1, 0, updatedProduct))
        );
    }

    // DELETE
    @DeleteMapping("/{id}")
    public ResponseEntity<DataResponse> deleteProduct(@PathVariable Long id) {
        productService.deleteProduct(id);
        return ResponseEntity.ok(
                new DataResponse(new ResponseData(0, 0, "Product deleted successfully"))
        );
    }

    // Response Wrapper Classes
    @Data
    private static class DataResponse {
        private ResponseData data;

        public DataResponse() {}

        public DataResponse(ResponseData data) {
            this.data = data;
        }
    }

    @Data
    @AllArgsConstructor
    private static class ResponseData {
        private int size;
        private int page;
        private Object content;
    }
}