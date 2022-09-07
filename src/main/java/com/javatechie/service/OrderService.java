package com.javatechie.service;

import com.javatechie.dto.Order;
import com.javatechie.util.Mapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
public class OrderService {


    private List<Order> orderList = new ArrayList<>();

    private Logger logger = LogManager.getLogger(OrderService.class);

    public Order addOrder(Order order) {
        logger.info("OrderService:addOrder method execution started..");
        logger.debug("OrderService:addOrder request {}", Mapper.mapToJsonString(order));
        order.setOrderPlacedDate(new Date());
        order.setTransactionId(UUID.randomUUID().toString());
        orderList.add(order);
        logger.debug("OrderService:addOrder response {}", Mapper.mapToJsonString(order));
        logger.info("OrderService:addOrder method execution ended..");
        return order;
    }

    public List<Order> getOrders() {
        List<Order> list = null;
        logger.info("OrderService:getOrders method execution started..");
        list = orderList;
        logger.debug("OrderService:getOrders response {}", Mapper.mapToJsonString(list));
        logger.info("OrderService:getOrders method execution ended..");
        return list;
    }

    public Order getOrder(int id) {
        logger.info("OrderService:getOrder method execution started..");
        logger.debug("OrderService:getOrder request {}", id);
        Order order = orderList.stream()
                .filter(ord -> ord.getId() == id)
                .findAny().orElseThrow(() -> new RuntimeException("Order not found with id : " + id));
        logger.debug("OrderService:getOrder response {}", Mapper.mapToJsonString(order));
        logger.info("OrderService:getOrder method execution ended..");
        return order;
    }
}
