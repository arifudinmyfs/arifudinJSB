package com.learnjava.arifudinJSB.services;

import com.learnjava.arifudinJSB.models.TransactionModel;
import com.learnjava.arifudinJSB.repositorys.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class TransactionService {

    @Autowired
    private TransactionRepository transactionRepository;

    // Create or Update Transaction
    public TransactionModel saveTransaction(TransactionModel transaction) {
        return transactionRepository.save(transaction);
    }

    // Read all Transactions
    public List<TransactionModel> getAllTransactions() {
        return transactionRepository.findAll();
    }

    // Read a single Transaction by ID
    public Optional<TransactionModel> getTransactionById(UUID id) {
        return transactionRepository.findById(id);
    }

    // Update a Transaction by ID
    public Optional<TransactionModel> updateTransaction(UUID id, TransactionModel updatedTransaction) {
        return transactionRepository.findById(id).map(existingTransaction -> {
            existingTransaction.setCustomerId(updatedTransaction.getCustomerId());
            existingTransaction.setNamaBarang(updatedTransaction.getNamaBarang());
            existingTransaction.setAmountTransaction(updatedTransaction.getAmountTransaction());
            existingTransaction.setTransactionDate(updatedTransaction.getTransactionDate());
            existingTransaction.setStatus(updatedTransaction.getStatus());
            existingTransaction.setDescription(updatedTransaction.getDescription());
            return transactionRepository.save(existingTransaction);
        });
    }

    // Delete a Transaction by ID
    public void deleteTransaction(UUID id) {
        transactionRepository.deleteById(id);
    }
}
