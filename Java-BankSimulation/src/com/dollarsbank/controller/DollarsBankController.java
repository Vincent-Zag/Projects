package com.dollarsbank.controller;

import com.dollarsbank.model.Customer;

import com.dollarsbank.model.Account;
import com.dollarsbank.utility.ConsolePrinterUtility;
import com.dollarsbank.utility.FileStorageUtility;

import java.util.List;
import java.util.Scanner;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


public class DollarsBankController {
    private List<Customer> customers;
    private Customer loggedInCustomer;
    private boolean isLoggedIn;

    public DollarsBankController() {
        customers = FileStorageUtility.loadData();
        isLoggedIn = false;
    }

    public void displayMenu(Scanner scanner) {
        while (true) {
            ConsolePrinterUtility.printMainMenu();
            int choice = scanner.nextInt();
            scanner.nextLine();
            
            switch (choice) {
                case 1:
                    deposit(scanner);
                    break;
                case 2:
                    withdraw(scanner);
                    break;
                case 3:
                    transferFunds(scanner);
                    break;
                case 4:
                    viewRecentTransactions(scanner);
                    break;
                case 5:
                    displayCustomerInformation();
                    break;
                case 6:
                    signOut();
                    return;
                default:
                    ConsolePrinterUtility.printError("Invalid choice. Please try again.");
            }
        }
    }

    public void displayLoginOrSignUpMenu(Scanner scanner) {
        while (true) {
            ConsolePrinterUtility.printLoginOrSignUpMenu();
            int choice = scanner.nextInt();
            scanner.nextLine();
            switch (choice) {
                case 1:
                    login(scanner);
                    break;
                case 2:
                    createAccount(scanner);
                    break;
                case 3:
                    System.exit(0);
                default:
                    ConsolePrinterUtility.printError("Invalid choice. Please try again.");
            }
        }
    }

    private void deposit(Scanner scanner) {
        if (!isLoggedIn) {
            ConsolePrinterUtility.printError("You must be logged in to perform this operation.");
            return;
        }

        ConsolePrinterUtility.printMessage("Enter the amount to deposit: ");
        double depositAmount = scanner.nextDouble();
        scanner.nextLine();
        
        if (depositAmount <= 0) {
            ConsolePrinterUtility.printError("Invalid deposit amount. Please enter a positive amount.");
            return;
        }

        Account selectedAccount = selectAccountForDeposit(scanner);

        if (selectedAccount == null) {
            ConsolePrinterUtility.printError("No account selected for deposit.");
            return;
        }

        double initialBalance = selectedAccount.getBalance();
        selectedAccount.deposit(depositAmount);
        double finalBalance = selectedAccount.getBalance();

        if (finalBalance == initialBalance + depositAmount) {
            ConsolePrinterUtility.printMessage("Deposit of $" + depositAmount + " was successful.");
            saveCustomerData(); 
        } else {
            ConsolePrinterUtility.printError("Deposit failed. Please try again.");
        }
    }


    private Account selectAccountForDeposit(Scanner scanner) {
        ConsolePrinterUtility.printMessage("Select an account for deposit: ");
        List<Account> customerAccounts = loggedInCustomer.getAccounts();

        for (int i = 0; i < customerAccounts.size(); i++) {
            ConsolePrinterUtility.printMessage((i + 1) + ". " + customerAccounts.get(i).getAccountNumber());
        }

        int choice = scanner.nextInt();
        scanner.nextLine();

        if (choice < 1 || choice > customerAccounts.size()) {
            ConsolePrinterUtility.printError("Invalid account selection.");
            return null;
        }
        return customerAccounts.get(choice - 1);
    } 


    private void withdraw(Scanner scanner) {
        if (!isLoggedIn) {
            ConsolePrinterUtility.printError("You must be logged in to perform this operation.");
            return;
        }

        ConsolePrinterUtility.printMessage("Enter the amount to withdraw: ");
        double withdrawalAmount = scanner.nextDouble();
        scanner.nextLine();
        
        if (withdrawalAmount <= 0) {
            ConsolePrinterUtility.printError("Invalid withdrawal amount. Please enter a positive amount.");
            return;
        }

        Account selectedAccount = selectAccountForWithdrawal(scanner);

        if (selectedAccount == null) {
            ConsolePrinterUtility.printError("No account selected for withdrawal.");
            return;
        }

        if (selectedAccount.withdraw(withdrawalAmount)) {
            ConsolePrinterUtility.printMessage("Withdrawal of $" + withdrawalAmount + " was successful.");
            saveCustomerData(); 
        } else {
            ConsolePrinterUtility.printError("Withdrawal failed. Please try again.");
        }
    }

    private Account selectAccountForWithdrawal(Scanner scanner) {
        ConsolePrinterUtility.printMessage("Select an account for withdrawal: ");
        List<Account> customerAccounts = loggedInCustomer.getAccounts();

        for (int i = 0; i < customerAccounts.size(); i++) {
            ConsolePrinterUtility.printMessage((i + 1) + ". " + customerAccounts.get(i).getAccountNumber());
        }

        int choice = scanner.nextInt();
        scanner.nextLine();
        
        if (choice < 1 || choice > customerAccounts.size()) {
            ConsolePrinterUtility.printError("Invalid account selection.");
            return null;
        }
        return customerAccounts.get(choice - 1);
    }


    private void transferFunds(Scanner scanner) {
        if (!isLoggedIn) {
            ConsolePrinterUtility.printError("You must be logged in to perform this operation.");
            return;
        }

        ConsolePrinterUtility.printMessage("Enter the amount to transfer: ");
        double transferAmount = scanner.nextDouble();
        scanner.nextLine();

        if (transferAmount <= 0) {
            ConsolePrinterUtility.printError("Invalid transfer amount. Please enter a positive amount.");
            return;
        }

        Account senderAccount = selectAccountForTransfer("from", scanner);

        if (senderAccount == null) {
            ConsolePrinterUtility.printError("No account selected for transfer.");
            return;
        }

        ConsolePrinterUtility.printMessage("Enter the recipient's account number: ");
        String recipientAccountNumber = scanner.next();
        scanner.nextLine();
        Account recipientAccount = findRecipientAccount(recipientAccountNumber);

        if (recipientAccount == null) {
            ConsolePrinterUtility.printError("Recipient's account not found.");
            return;
        }

        if (senderAccount.getBalance() < transferAmount) {
            ConsolePrinterUtility.printError("Insufficient balance for the transfer.");
            return;
        }

        double senderInitialBalance = senderAccount.getBalance();
        double recipientInitialBalance = recipientAccount.getBalance();

        senderAccount.withdraw(transferAmount);
        recipientAccount.deposit(transferAmount);

        double senderFinalBalance = senderAccount.getBalance();
        double recipientFinalBalance = recipientAccount.getBalance();

        if (senderFinalBalance == senderInitialBalance - transferAmount && recipientFinalBalance == recipientInitialBalance + transferAmount) {
            LocalDateTime timestamp = LocalDateTime.now();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

            String formattedTimestamp = timestamp.format(formatter);
            String senderTransaction = "Transferred $" + transferAmount + " to " + recipientAccount.getAccountNumber() + " on " + formattedTimestamp;
            String recipientTransaction = "Received $" + transferAmount + " from " + senderAccount.getAccountNumber() + " on " + formattedTimestamp;

            senderAccount.addToTransactionHistory(senderTransaction);
            recipientAccount.addToTransactionHistory(recipientTransaction);

            ConsolePrinterUtility.printMessage("Transfer of $" + transferAmount + " was successful.");
            saveCustomerData();
        } else {
            ConsolePrinterUtility.printError("Transfer failed. Check your balance.");
        }
    }


    private Account selectAccountForTransfer(String direction, Scanner scanner) {
        ConsolePrinterUtility.printMessage("Select an account to transfer " + direction + ": ");
        List<Account> customerAccounts = loggedInCustomer.getAccounts();

        for (int i = 0; i < customerAccounts.size(); i++) {
            ConsolePrinterUtility.printMessage((i + 1) + ". " + customerAccounts.get(i).getAccountNumber());
        }

        int choice = scanner.nextInt();
        scanner.nextLine();
        
        if (choice < 1 || choice > customerAccounts.size()) {
            ConsolePrinterUtility.printError("Invalid account selection.");
            return null;
        }
        return customerAccounts.get(choice - 1);
    }

    private Account findRecipientAccount(String recipientAccountNumber) {
        for (Customer customer : customers) {
            for (Account account : customer.getAccounts()) {
                if (account.getAccountNumber().equals(recipientAccountNumber)) {
                    return account;
                }
            }
        }
        return null;
    }

    private void viewRecentTransactions(Scanner scanner) {
        if (!isLoggedIn) {
            ConsolePrinterUtility.printError("You must be logged in to perform this operation.");
            return;
        }

        Account selectedAccount = selectAccountForTransactionHistory(scanner);

        if (selectedAccount == null) {
            ConsolePrinterUtility.printError("No account selected for viewing transactions.");
            return;
        }

        List<String> recentTransactions = selectedAccount.getTransactionHistory();

        if (recentTransactions.isEmpty()) {
            ConsolePrinterUtility.printMessage("No transactions for this account.");
        } else {
            ConsolePrinterUtility.printMessage("Transaction History for Account " + selectedAccount.getAccountNumber() + ":");

            for (String transaction : recentTransactions) {
                ConsolePrinterUtility.printMessage(transaction);
            }
        }
    }

    private Account selectAccountForTransactionHistory(Scanner scanner) {
        ConsolePrinterUtility.printMessage("Select an account for viewing transactions: ");
        List<Account> customerAccounts = loggedInCustomer.getAccounts();

        for (int i = 0; i < customerAccounts.size(); i++) {
            ConsolePrinterUtility.printInfo((i + 1) + ". " + customerAccounts.get(i).getAccountNumber());
        }

        int choice = scanner.nextInt();
        scanner.nextLine();
        if (choice < 1 || choice > customerAccounts.size()) {
            ConsolePrinterUtility.printError("Invalid account selection.");
            return null;
        }

        return customerAccounts.get(choice - 1);
    }

    private void displayCustomerInformation() {
        if (!isLoggedIn) {
            ConsolePrinterUtility.printError("You must be logged in to view customer information.");
            return;
        }

        ConsolePrinterUtility.printInfo("Customer Information for " + loggedInCustomer.getUsername() + ":");
        ConsolePrinterUtility.printInfo("Full Name: " + loggedInCustomer.getFullName());
        ConsolePrinterUtility.printInfo("Address: " + loggedInCustomer.getAddress());

        List<Account> customerAccounts = loggedInCustomer.getAccounts();
        for (int i = 0; i < customerAccounts.size(); i++) {
            ConsolePrinterUtility.printInfo("Account " + (i + 1) + ":");
            ConsolePrinterUtility.printInfo("   Account Number: " + customerAccounts.get(i).getAccountNumber());
            ConsolePrinterUtility.printInfo("   Balance: $" + customerAccounts.get(i).getBalance());
        }
    }


    private void login(Scanner scanner) {
        ConsolePrinterUtility.printMessage("Enter your username: ");
        String username = scanner.nextLine();
        ConsolePrinterUtility.printMessage("Enter your password: ");
        String password = scanner.nextLine();

        Customer customer = findCustomerByUsername(username);

        if (customer != null && customer.validatePassword(password)) {
            loggedInCustomer = customer;
            isLoggedIn = true;
            ConsolePrinterUtility.printMessage("Login successful. Welcome, " + customer.getUsername() + "!");
            displayMenu(scanner);
        } else {
            ConsolePrinterUtility.printError("Invalid username or password.");
        }
    }

    private void createAccount(Scanner scanner) {

        ConsolePrinterUtility.printMessage("Enter your username: ");
        String username = scanner.nextLine();

        if (isUsernameTaken(username)) {
            ConsolePrinterUtility.printError("Username already exists. Please choose a different username.");
            return;
        }

        ConsolePrinterUtility.printMessage("Enter your password: ");
        String password = scanner.nextLine();
        
        ConsolePrinterUtility.printMessage("Enter your First Name: ");
        String fname = scanner.nextLine();
        
        ConsolePrinterUtility.printMessage("Enter your Last Name: ");
        String lname = scanner.nextLine();
        
        ConsolePrinterUtility.printMessage("Enter your Address: ");
        String address = scanner.nextLine();

        ConsolePrinterUtility.printMessage("Enter your initial deposit amount: ");
        double initialDeposit = scanner.nextDouble();

        if (initialDeposit <= 0) {
            ConsolePrinterUtility.printError("Initial deposit must be greater than zero.");
            return;
        }

        Customer newCustomer = new Customer(username, password, fname, lname, address);
        Account newAccount = new Account(generateAccountNumber(), initialDeposit);
        newCustomer.addAccount(newAccount);
        customers.add(newCustomer);

        ConsolePrinterUtility.printMessage("Account created successfully.");
        saveCustomerData();
    }

    private boolean isUsernameTaken(String username) {
        return customers.stream().anyMatch(customer -> customer.getUsername().equals(username));
    }

    private String generateAccountNumber() {
        int accountCount = customers.stream().mapToInt(customer -> customer.getAccounts().size()).sum();
        return "ACC" + (accountCount + 1);
    }


    private void signOut() {
        loggedInCustomer = null;
        isLoggedIn = false;
        ConsolePrinterUtility.printMessage("You have been signed out. Goodbye!");
    }

    public boolean isLoggedIn() {
        return isLoggedIn;
    }

    private Customer findCustomerByUsername(String username) {
        for (Customer customer : customers) {
            if (customer.getUsername().equals(username)) {
                return customer;
            }
        }
        return null;
    }

    public void saveCustomerData() {
        FileStorageUtility.saveData(customers);
    }
}
