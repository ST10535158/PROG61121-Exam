package za.co.mattress.report;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

class ProductTest {
    private Product createSample() {
        String[] names = {"Cozy Nights Monarch", "Restonic Barbados", "Sealy Phuket"};
        int[][] sales = {{25, 18, 20}, {15, 10, 12}, {18, 15, 20}};
        return new Product(names, sales);
    }

    @Test
    void GetTotalProductSales_WithValidData_ReturnsCorrectValue() {
        assertEquals(153, createSample().GetTotalProductsSold());
    }

    @Test
    void GetAverageSales_WithValidData_ReturnsCorrectValue() {
        assertEquals(17.0, createSample().GetAverageProductSold(), 0.0001);
    }

    @Test
    void GetMostPopularProduct_WithValidData_ReturnsHighestCombinedSales() {
        assertEquals("Cozy Nights Monarch (63)", createSample().GetMostPopularProduct());
    }

    @Test
    void constructor_RejectsNegativeSales() {
        assertThrows(IllegalArgumentException.class,
            () -> new Product(new String[]{"Test"}, new int[][]{{1, -1, 2}}));
    }
}
