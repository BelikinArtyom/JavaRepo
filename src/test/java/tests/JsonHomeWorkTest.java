package tests;

import json.models.OrderAdress;
import json.models.OrderDetails;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.InputStream;

public class JsonHomeWorkTest {

    @Test
    void jsonTestImprovedJackson() throws Exception {

        ClassLoader cl = getClass().getClassLoader();
        try (InputStream is = cl.getResourceAsStream("JaySon.json")) {

            ObjectMapper mapper = new ObjectMapper();
            OrderDetails orderDetails = mapper.readValue(is, OrderDetails.class);

            assertEquals("John Smith", orderDetails.getName());
            assertEquals(20223, orderDetails.getSku());
            assertEquals(23.95, orderDetails.getPrice());

            OrderAdress address = orderDetails.getShipTo();
            assertNotNull(address);
            assertEquals("Jane Smith", address.getName());
            assertEquals("123 Maple Street", address.getAddress());
            assertEquals("Pretendville", address.getCity());
            assertEquals("NY", address.getState());
            assertEquals(12345, address.getZip());
        }
    }
}

