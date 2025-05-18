package lab.mongoDb.springboot_mongodb.service;

import lab.mongoDb.springboot_mongodb.dao.MongoDao;
import lab.mongoDb.springboot_mongodb.models.Address;
import lab.mongoDb.springboot_mongodb.models.Order;
import lombok.extern.log4j.Log4j2;
import net.datafaker.Faker;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.stream.IntStream;

@Log4j2
@Service
public class OrderService {

    private final MongoDao mongoDao;

    private final Faker faker;

    public OrderService(MongoDao mongoDao, Faker faker) {
        this.mongoDao = mongoDao;
        this.faker = faker;
    }

    public List<Order> createBulkOrder(int orderCount) {
        log.info("Creating bulk order of {} at {}", orderCount, Instant.now());
        return IntStream.rangeClosed(0, orderCount).mapToObj(i -> {
            return mongoDao.createOrder(createRandomOrder());
        }).toList();
    }

    public Order getOrderById(BigDecimal orderId) {
        log.info("Fetching bulk order of {} at : {}", orderId, Instant.now());
        return mongoDao.getOrderById(orderId);
    }

    public List<Order> getOrders() {
        log.info("Fetching all orders at : {}", Instant.now());
        return mongoDao.getOrders();
    }

    private Order createRandomOrder() {
        return Order.builder()
                .orderId(BigInteger.valueOf(faker.number().positive()))
                .orderCreateDate(Instant.now())
                .orderDeliveryDate(Instant.now().plus(faker.number().numberBetween(1, 100), ChronoUnit.DAYS))
                .orderPrice(faker.number().numberBetween(1, 100000))
                .orderCount(faker.number().numberBetween(1, 100))
                .deliveryAddress(getRandomAddress())
                .pickupAddress(getRandomAddress())
                .isCancelled(faker.bool().bool())
                .isValid(faker.bool().bool())
                .originCountry(getRandomCountryName())
                .customerEmail(faker.internet().safeEmailAddress())
                .orderDesc(faker.commerce().productName())
                .build();
    }

    private List<String> getRandomCountryName() {
        return IntStream.rangeClosed(0, 3).mapToObj(i -> faker.country().name()).toList();
    }

    private Address getRandomAddress() {
        return Address.builder()
                .addressLine(faker.address().streetAddress())
                .state(faker.address().state())
                .country(faker.address().country())
                .pinCode(faker.address().postcode())
                .build();
    }
}
