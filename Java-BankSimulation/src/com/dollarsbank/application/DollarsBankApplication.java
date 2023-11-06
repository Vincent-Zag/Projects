package com.dollarsbank.application;

import java.util.Scanner;
import com.dollarsbank.utility.ConsolePrinterUtility;
import com.dollarsbank.controller.DollarsBankController;

public class DollarsBankApplication {
    public static void main(String[] args) {
        DollarsBankController controller = new DollarsBankController();
        Scanner scanner = new Scanner(System.in);
       
        ConsolePrinterUtility.printInfo("Welcome to Dollars Bank!");
        System.out.println();
        while (true) {
            if (controller.isLoggedIn()) {
                controller.displayMenu(scanner);
            } else {
                controller.displayLoginOrSignUpMenu(scanner);
            }
        }
    }
}
