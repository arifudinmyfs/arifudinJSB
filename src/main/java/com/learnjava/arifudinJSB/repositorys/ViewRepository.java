package com.learnjava.arifudinJSB.repositorys;

import com.learnjava.arifudinJSB.models.ViewCustomerTransaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Repository
public interface ViewRepository extends JpaRepository<ViewCustomerTransaction, UUID> {
    @Query("SELECT new ViewCustomerTransaction(t.id, c.customerName, t.amountTransaction, t.transactionDate, t.status) " +
            "FROM TransactionModel t " +
            "JOIN t.customerId c")
    List<ViewCustomerTransaction> findCustomerTransactionView();
}
