package com.learnjava.arifudinJSB.models;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Data
@NoArgsConstructor
@Table(name = "customers")
public class CustomerModel {

    @Id
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "transaction_id", referencedColumnName = "id")
    private TransactionModel transactionId;

    @Column(name = "customer_name", nullable = false)
    private String customerName;

    @Column(name = "customer_email", unique = true, nullable = false)
    private String customerEmail;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "address")
    private String address;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    public void setCreatedAt() {
        if (this.createdAt == null) {
            this.createdAt = LocalDateTime.now();
        }
    }

    @PrePersist
    public void generateId() {
        if (this.id == null) {
            this.id = UUID.randomUUID();
        }
    }
}
