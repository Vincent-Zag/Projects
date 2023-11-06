package com.dollarsbank.utility;

import com.dollarsbank.model.Customer;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FileStorageUtility {
    private static final String DATA_FILE = "customer_data.txt";

    public static void saveData(List<Customer> customers) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(DATA_FILE))) {
            oos.writeObject(customers);
            ConsolePrinterUtility.printMessage("Data saved successfully.");
        } catch (IOException e) {
            ConsolePrinterUtility.printError("Failed to save data: " + e.getMessage());
        }
    }

    @SuppressWarnings("unchecked")
	public static List<Customer> loadData() {
        List<Customer> customers = new ArrayList<>();

        if (fileExists(DATA_FILE)) {
            try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(DATA_FILE))) {
                customers = (List<Customer>) ois.readObject();
                ConsolePrinterUtility.printMessage("Data loaded successfully.");
            } catch (IOException | ClassNotFoundException e) {
                ConsolePrinterUtility.printError("Failed to load data: " + e.getMessage());
            }
        }

        return customers;
    }

    private static boolean fileExists(String filename) {
        File file = new File(filename);
        return file.exists() && !file.isDirectory();
    }
}
