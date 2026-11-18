package za.co.mattress.report;

/** Defines the calculations required for the product sales report. */
public interface IProduct {
    int GetTotalProductsSold();
    double GetAverageProductSold();
    String GetMostPopularProduct();
}
