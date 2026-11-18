package za.co.mattress.report;

/** Responsible for displaying the calculated sales report. */
public class PrintProduct {
    public void PrintTotalProductSales(IProduct product) {
        System.out.println("TOTAL PRODUCT SALES: " + product.GetTotalProductsSold());
    }

    public void PrintAverageProductSales(IProduct product) {
        System.out.printf("AVERAGE PRODUCT SALES: %.1f%n", product.GetAverageProductSold());
    }

    public void PrintMostPopularProduct(IProduct product) {
        System.out.println("MOST POPULAR PRODUCT: " + product.GetMostPopularProduct());
    }

    public void PrintReport(IProduct product) {
        System.out.println("\nPRODUCT SALES REPORT - 2026");
        System.out.println("**************************************************");
        PrintTotalProductSales(product);
        PrintAverageProductSales(product);
        PrintMostPopularProduct(product);
    }
}
