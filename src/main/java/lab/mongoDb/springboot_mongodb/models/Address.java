package lab.mongoDb.springboot_mongodb.models;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class Address {
    private String addressLine;
    private String pinCode;
    private String state;
    private String country;
}
