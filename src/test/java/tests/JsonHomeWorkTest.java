package tests;

import json.models.OrderDetails;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonHomeWorkTest {

    @Test
    void jsonTestImprovedJackson() throws Exception {
        String json = """
        {
          "name"   : "John Smith",
          "sku"    : 20223,
          "price"  : 23.95,
          "shipTo" : {
                  "name" : "Jane Smith",
                  "address" : "123 Maple Street",
                  "city" : "Pretendville",
                  "state" : "NY",
                  "zip"   : 12345
          }
        }
        """;

        ObjectMapper mapper = new ObjectMapper();
        OrderDetails orderDetails = mapper.readValue(json, OrderDetails.class);

        assertEquals("John Smith", orderDetails.name);
        assertEquals(20223, orderDetails.sku);
        assertEquals(23.95, orderDetails.price);

        assertNotNull(orderDetails.shipTo);
        assertEquals("Jane Smith", orderDetails.shipTo.name);
        assertEquals("123 Maple Street", orderDetails.shipTo.address);
        assertEquals("Pretendville", orderDetails.shipTo.city);
        assertEquals("NY", orderDetails.shipTo.state);
        assertEquals(12345, orderDetails.shipTo.zip);
    }

}

