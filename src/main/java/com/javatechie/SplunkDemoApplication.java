package com.javatechie;

import com.javatechie.dto.Order;
import com.javatechie.service.OrderService;
import com.javatechie.util.Mapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@SpringBootApplication
@RestController
@RequestMapping("/orders")
public class SplunkDemoApplication {

    @Autowired
    private OrderService service;

    private Logger logger = LogManager.getLogger(OrderService.class);

    @PostMapping
    public Order placeOrder(@RequestBody Order order) {
        logger.info("OrderController::placeOrder request  {} ", Mapper.mapToJsonString(order));
        Order addOrder = service.addOrder(order);
        logger.info("OrderController::placeOrder response  {} ", Mapper.mapToJsonString(addOrder));
        return addOrder;
    }

    @GetMapping
    public List<Order> getOrders() {
        logger.info("OrderController::getOrders fetch all orders from system");
        List<Order> orders = service.getOrders();
        logger.info("OrderController::getOrders response {}", orders);
        return orders;
    }

    @GetMapping("/{id}")
    public Order getOrder(@PathVariable int id) {
        logger.info("OrderController::getOrder fetch order by id {}", id);
        Order order = service.getOrder(id);
        logger.info("OrderController::getOrder response {}", Mapper.mapToJsonString(order));
        return order;
    }

    public static void main(String[] args) {
        SpringApplication.run(SplunkDemoApplication.class, args);
    }

}
