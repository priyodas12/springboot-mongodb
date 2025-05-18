package lab.mongoDb.springboot_mongodb.dao;

import lab.mongoDb.springboot_mongodb.models.Order;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public class MongoDao {

    private final MongoTemplate mongoTemplate;

    public MongoDao(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    public Order getOrderById(BigDecimal orderId) {
        return mongoTemplate.findById(orderId, Order.class);
    }

    public List<Order> getOrders() {
        return mongoTemplate.findAll(Order.class);
    }

    public Order createOrder(Order order) {
        return mongoTemplate.save(order);
    }
}
