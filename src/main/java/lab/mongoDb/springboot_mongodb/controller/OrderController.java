package lab.mongoDb.springboot_mongodb.controller;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.constraints.Min;
import lab.mongoDb.springboot_mongodb.models.Order;
import lab.mongoDb.springboot_mongodb.service.OrderService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Validated
@RequestMapping("/api/v1")
@RestController
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @Operation(description = "create bulk number of order by providing count of order", method = "post")
    @PostMapping("/orders/{count}")
    public ResponseEntity<List<Order>> createOrders(@PathVariable("count") @Min(value = 5, message = "minimum bulk count is 5") int count) {
        Optional<List<Order>> ordersOptional = Optional.ofNullable(orderService.createBulkOrder(count));
        return ordersOptional.map(orders -> ResponseEntity.status(201).body(orders)).orElseGet(() -> ResponseEntity.status(500).body(null));
    }

    @Operation(description = "fetch order with order id", method = "get")
    @GetMapping("/orders/{orderId}")
    public ResponseEntity<Order> fetchOrder(@PathVariable("orderId") int orderId) {
        Optional<Order> ordersOptional = Optional.ofNullable(orderService.getOrderById(BigDecimal.valueOf(orderId)));
        return ordersOptional.map(order -> ResponseEntity.status(200).body(order)).orElseGet(() -> ResponseEntity.status(500).body(null));
    }

    @Operation(description = "fetch all orders from order collection", method = "get")
    @GetMapping("/orders")
    public ResponseEntity<List<Order>> fetchOrders() {
        Optional<List<Order>> ordersOptional = Optional.ofNullable(orderService.getOrders());
        return ordersOptional.map(orders -> ResponseEntity.status(200).body(orders)).orElseGet(() -> ResponseEntity.status(500).body(null));
    }
}
