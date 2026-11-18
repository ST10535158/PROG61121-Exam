package za.co.mattress.app;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

class ProductCalculationsTest {
    @Test
    void GetVat_WithValidData_ReturnsCorrectValue() {
        assertEquals(525.0, new ProductCalculations(3500).GetVat(), 0.0001);
    }

    @Test
    void GetTotal_WithValidData_ReturnsCorrectValue() {
        assertEquals(4200.0, new ProductCalculations(3500).GetTotal(), 0.0001);
    }

    @Test
    void GetCommission_WithValidData_ReturnsCorrectValue() {
        assertEquals(175.0, new ProductCalculations(3500).GetCommission(), 0.0001);
    }

    @Test
    void constructor_RejectsNegativePrice() {
        assertThrows(IllegalArgumentException.class, () -> new ProductCalculations(-1));
    }
}
