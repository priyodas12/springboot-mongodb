package lab.mongoDb.springboot_mongodb.models;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigInteger;
import java.time.Instant;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Document(collection = "orders")
public class Order {

    @Id
    private BigInteger orderId;
    private Address deliveryAddress;
    private Address pickupAddress;
    private long orderCount;
    private Instant orderCreateDate;
    private Instant orderDeliveryDate;
    private boolean isCancelled;
    private boolean isValid;
    private double orderPrice;
    private List<String> originCountry;
    private String customerEmail;
    private String orderDesc;
}
