package com.learnjava.arifudinJSB.repositorys;

import com.learnjava.arifudinJSB.models.TransactionModel;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.UUID;

public interface TransactionRepository extends JpaRepository<TransactionModel, UUID> {
    // Tambahkan custom query jika diperlukan
}