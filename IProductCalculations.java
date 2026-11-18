package za.co.mattress.app;

/** Defines the sale calculation methods. */
public interface IProductCalculations {
    double GetVat();
    double GetCommission();
    double GetTotal();
}
