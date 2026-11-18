package za.co.mattress.report;

import java.util.Scanner;

/** Collects monthly sales figures and generates the report. */
public class Main {
    private static final String[] PRODUCTS = {
        "Cozy Nights Monarch", "Restonic Barbados", "Sealy Phuket"
    };

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int[][] sales = new int[PRODUCTS.length][3];

        for (int product = 0; product < PRODUCTS.length; product++) {
            for (int month = 0; month < 3; month++) {
                sales[product][month] = readNonNegativeInt(scanner,
                    "Enter number of sales for month " + (month + 1) + " for "
                    + PRODUCTS[product] + " mattress: ");
            }
            System.out.println();
        }

        Product productReport = new Product(PRODUCTS, sales);
        new PrintProduct().PrintReport(productReport);
    }

    private static int readNonNegativeInt(Scanner scanner, String prompt) {
        while (true) {
            System.out.print(prompt);
            String input = scanner.nextLine().trim();
            try {
                int value = Integer.parseInt(input);
                if (value >= 0) return value;
            } catch (NumberFormatException ignored) {
                // Display a helpful message and ask again.
            }
            System.out.println("Please enter a whole number that is zero or greater.");
        }
    }
}
