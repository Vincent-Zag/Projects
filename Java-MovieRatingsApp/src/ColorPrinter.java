public class ColorPrinter {
    private static final String ANSI_RESET = "\u001B[0m";
    private static final String ANSI_RED = "\u001B[31m";
    private static final String ANSI_GREEN = "\u001B[32m";
    private static final String ANSI_YELLOW = "\u001B[33m";
    private static final String ANSI_CYAN = "\u001B[36m";
    private static final String ANSI_BG_BLACK = "\u001B[40m";


    public static String colorText(String text, String textColor, String bgColor) {
        return textColor + bgColor + text + ANSI_RESET;
    }

    public static String colorTextRed(String text) {
        return colorText(text, ANSI_RED, ANSI_BG_BLACK);
    }
    
    public static String colorTextYellow(String text) {
        return colorText(text, ANSI_YELLOW, ANSI_BG_BLACK);
    }

    public static String colorTextGreen(String text) {
        return colorText(text, ANSI_GREEN, ANSI_BG_BLACK);
    }

    public static String colorTextCyan(String text) {
        return colorText(text, ANSI_CYAN, ANSI_BG_BLACK);
    }

}
