package com.learnjava.arifudinJSB.models;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Data
@NoArgsConstructor
@Table(name = "transactions")
public class TransactionModel {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "customer_id", referencedColumnName = "id")
    private CustomerModel customerId; // Relasi ke entitas Customer

    @Column(name = "nama_barang", nullable = false)
    private String namaBarang;

    @Column(name = "amount_transaction", nullable = false)
    private BigDecimal amountTransaction;

    @Column(name = "transaction_date", nullable = false)
    private LocalDateTime transactionDate;

    @Column(name = "status", nullable = false)
    private String status;

    @Column(name = "description")
    private String description;

    @PrePersist
    public void generateId() {
        if (this.id == null) {
            this.id = UUID.randomUUID(); // Generate UUID jika ID belum diatur
        }
    }
}
