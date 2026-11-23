package com.naturehoney;

import org.junit.jupiter.api.Test;
import java.math.BigDecimal;
import static org.junit.jupiter.api.Assertions.*;

class ProductTest {
    @Test
    void productStoresBasicDetails() {
        Product p = new Product(1, "Wild Forest Honey", "Natural honey",
                "500 g", new BigDecimal("349.00"), "forest-honey.jpg", true);
        assertEquals("Wild Forest Honey", p.name);
        assertEquals(new BigDecimal("349.00"), p.price);
        assertTrue(p.active);
    }
}
