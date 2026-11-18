package za.co.mattress.report;

/** Stores three months of sales for each product and calculates report values. */
public class Product implements IProduct {
    private final String[] productNames;
    private final int[][] monthlySales;
    private final int[] productTotals;

    public Product(String[] productNames, int[][] monthlySales) {
        if (productNames == null || monthlySales == null || productNames.length == 0
                || productNames.length != monthlySales.length) {
            throw new IllegalArgumentException("Product names and sales data must have matching, non-empty rows.");
        }
        this.productNames = productNames.clone();
        this.monthlySales = new int[monthlySales.length][];
        this.productTotals = new int[monthlySales.length];
        for (int row = 0; row < monthlySales.length; row++) {
            if (monthlySales[row] == null || monthlySales[row].length != 3) {
                throw new IllegalArgumentException("Each product must have sales for exactly three months.");
            }
            this.monthlySales[row] = monthlySales[row].clone();
            for (int value : monthlySales[row]) {
                if (value < 0) throw new IllegalArgumentException("Sales cannot be negative.");
                productTotals[row] += value;
            }
        }
    }

    /** Returns the total number of units sold across all products and months. */
    @Override
    public int GetTotalProductsSold() {
        int total = 0;
        for (int productTotal : productTotals) total += productTotal;
        return total;
    }

    /** Returns the average units sold per product-month entry. */
    @Override
    public double GetAverageProductSold() {
        int entries = productNames.length * 3;
        return entries == 0 ? 0.0 : (double) GetTotalProductsSold() / entries;
    }

    /** Returns the product with the greatest combined sales over the three months. */
    @Override
    public String GetMostPopularProduct() {
        int highestIndex = 0;
        for (int i = 1; i < productTotals.length; i++) {
            if (productTotals[i] > productTotals[highestIndex]) highestIndex = i;
        }
        return productNames[highestIndex] + " (" + productTotals[highestIndex] + ")";
    }

    public String[] getProductNames() { return productNames.clone(); }
    public int[][] getMonthlySales() {
        int[][] copy = new int[monthlySales.length][];
        for (int i = 0; i < monthlySales.length; i++) copy[i] = monthlySales[i].clone();
        return copy;
    }
    public int[] getProductTotals() { return productTotals.clone(); }
}
