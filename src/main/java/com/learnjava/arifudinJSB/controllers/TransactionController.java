package com.learnjava.arifudinJSB.controllers;

import com.learnjava.arifudinJSB.models.TransactionModel;
import com.learnjava.arifudinJSB.services.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/transactions")
public class TransactionController {

    @Autowired
    private TransactionService transactionService;

    // Create or Update Transaction
    @PostMapping
    public ResponseEntity<TransactionModel> createOrUpdateTransaction(@RequestBody TransactionModel transaction) {
        TransactionModel savedTransaction = transactionService.saveTransaction(transaction);
        return new ResponseEntity<>(savedTransaction, HttpStatus.CREATED);
    }

    // Get all Transactions
    @GetMapping
    public ResponseEntity<List<TransactionModel>> getAllTransactions() {
        List<TransactionModel> transactions = transactionService.getAllTransactions();
        return new ResponseEntity<>(transactions, HttpStatus.OK);
    }

    // Get a Transaction by ID
    @GetMapping("/{id}")
    public ResponseEntity<TransactionModel> getTransactionById(@PathVariable UUID id) {
        Optional<TransactionModel> transaction = transactionService.getTransactionById(id);
        return transaction.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    // Update a Transaction by ID
    @PutMapping("/{id}")
    public ResponseEntity<TransactionModel> updateTransaction(@PathVariable UUID id, @RequestBody TransactionModel updatedTransaction) {
        Optional<TransactionModel> updated = transactionService.updateTransaction(id, updatedTransaction);
        return updated.map(transaction -> new ResponseEntity<>(transaction, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    // Delete a Transaction by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTransaction(@PathVariable UUID id) {
        transactionService.deleteTransaction(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
