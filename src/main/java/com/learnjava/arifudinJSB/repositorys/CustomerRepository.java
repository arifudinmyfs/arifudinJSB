package com.learnjava.arifudinJSB.repositorys;

import com.learnjava.arifudinJSB.models.CustomerModel;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.UUID;

public interface CustomerRepository extends JpaRepository<CustomerModel, UUID> {
    // Tambahkan custom query jika diperlukan
}