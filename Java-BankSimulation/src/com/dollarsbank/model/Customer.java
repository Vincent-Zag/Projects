package com.dollarsbank.model;

import java.util.ArrayList;
import java.util.List;
import java.io.Serializable;

public class Customer implements Serializable{
	private static final long serialVersionUID = 1L;
	private String username;
    private String password;
    private String fullname;
    private String address;
    private List<Account> accounts;

    public Customer(String username, String password, String fname, String lname, String address) {
        this.username = username;
        this.password = password;
        this.fullname = fname + " " + lname;
        this.address = address;
        this.accounts = new ArrayList<>();
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getFullName() {
        return fullname;
    }
    
    public String getAddress() {
        return address;
    }

    public List<Account> getAccounts() {
        return accounts;
    }

    public void addAccount(Account account) {
        accounts.add(account);
    }

    public boolean validatePassword(String password) {
        return this.password.equals(password);
    }

    public Account findAccountByNumber(String accountNumber) {
        for (Account account : accounts) {
            if (account.getAccountNumber().equals(accountNumber)) {
                return account;
            }
        }
        return null; 
    }
}
