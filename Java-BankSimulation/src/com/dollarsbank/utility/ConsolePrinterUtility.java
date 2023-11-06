package com.dollarsbank.utility;

public class ConsolePrinterUtility {
    public static void printMainMenu() {
        System.out.println(ColorsUtility.colorTextCyan("*=*=*=*=*=* Main Menu *=*=*=*=*"));
        System.out.println(ColorsUtility.colorTextCyan("          1. Deposit           "));
        System.out.println(ColorsUtility.colorTextCyan("          2. Withdraw          "));
        System.out.println(ColorsUtility.colorTextCyan("         3. Transfer Funds     "));
        System.out.println(ColorsUtility.colorTextCyan("  4. View Recent Transactions  "));
        System.out.println(ColorsUtility.colorTextCyan("5. Display Customer Information"));
        System.out.println(ColorsUtility.colorTextCyan("          6. Sign Out          "));
        System.out.println(ColorsUtility.colorTextCyan("*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*"));
        System.out.println();
        System.out.print(ColorsUtility.colorTextGreen(  "Enter your choice: "));
    }

    public static void printLoginOrSignUpMenu() {
        System.out.println(ColorsUtility.colorTextCyan("^v^v^ Login/Sign-Up Menu ^v^v^"));
        System.out.println(ColorsUtility.colorTextCyan("           1. Login		      "));
        System.out.println(ColorsUtility.colorTextCyan("      2. Create an Account    "));
        System.out.println(ColorsUtility.colorTextCyan("            3.Exit            "));
        System.out.println(ColorsUtility.colorTextCyan("^v^v^v^v^v^v^v^v^v^v^v^v^v^v^v"));
        System.out.println();
        System.out.print(ColorsUtility.colorTextGreen("Enter your choice: "));
    }

    public static void printMessage(String message) {
        System.out.println(ColorsUtility.colorTextGreen(message));
    }

    public static void printError(String error) {
        System.out.println(ColorsUtility.colorTextRed("Error: " + error));
    }
    
    public static void printInfo(String message) {
        System.out.println(ColorsUtility.colorTextYellow(message));
    }

}
