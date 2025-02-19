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
@Table(name = "customer_transaction_view")
public class ViewCustomerTransaction {

    @Id
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "customer_id", referencedColumnName = "id")
    private CustomerModel customerId;

    @ManyToOne
    @JoinColumn(name = "transaction_id", referencedColumnName = "id")
    private TransactionModel transactionId;

    @Column(name = "customer_name")
    private String customerName;

    @Column(name = "transaction_amount")
    private BigDecimal transactionAmount;

    @Column(name = "transaction_date")
    private LocalDateTime transactionDate;

    @Column(name = "status")
    private String status;

    // Konstruktor yang sesuai dengan parameter dalam query HQL
    public ViewCustomerTransaction(UUID id, String customerName, BigDecimal transactionAmount, LocalDateTime transactionDate, String status) {
        this.id = id;
        this.customerName = customerName;
        this.transactionAmount = transactionAmount;
        this.transactionDate = transactionDate;
        this.status = status;
    }
}
