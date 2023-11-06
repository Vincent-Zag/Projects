package com.dollarsbank.model;

import java.util.ArrayList;
import java.util.List;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Account implements Serializable{
	private static final long serialVersionUID = 1L;
	private String accountNumber;
    private double balance;
    private List<String> transactionHistory;

    public Account(String accountNumber, double initialBalance) {
        this.accountNumber = accountNumber;
        this.balance = initialBalance;
        this.transactionHistory = new ArrayList<>();
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public double getBalance() {
        return balance;
    }

    public List<String> getTransactionHistory() {
        return transactionHistory;
    }

    public void deposit(double amount) {
        this.balance += amount;
        String transaction = getTimestamp() + " - Deposited $" + amount;
        addToTransactionHistory(transaction);
    }

    public boolean withdraw(double amount) {
        if (balance >= amount) {
            this.balance -= amount;
            String transaction = getTimestamp() + " - Withdrawn $" + amount;
            addToTransactionHistory(transaction);
            return true;
        }
        return false;
    }

    public void addToTransactionHistory(String transaction) {
        transactionHistory.add(transaction);
    }

    public void transfer(Account toAccount, double amount) {
        if (balance >= amount) {
            balance -= amount;
            toAccount.deposit(amount);
            String transaction = getTimestamp() + " - Transferred $" + amount + " to " + toAccount.getAccountNumber();
            addToTransactionHistory(transaction);
        } else {
            String transaction = getTimestamp() + " - Insufficient balance to transfer: $" + amount;
            addToTransactionHistory(transaction);
        }
    }

    private String getTimestamp() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime timestamp = LocalDateTime.now();
        return timestamp.format(formatter);
    }
    
}
