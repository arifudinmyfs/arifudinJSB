package com.learnjava.arifudinJSB.repositorys;

import com.learnjava.arifudinJSB.models.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ProductRepository extends JpaRepository<Product, Long> {
    @Query("SELECT p FROM Product p WHERE " + "(:name IS NULL OR p.name LIKE %:name%)")
    Page<Product> findAllWithFilter(
            @Param("name") String name,
            Pageable pageable
    );
}
