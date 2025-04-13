package com.mystore.orders.service;

import com.mystore.orders.dto.OrderRequest;
import com.mystore.orders.dto.OrderResponse;
import com.mystore.orders.dto.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Random;

@Service
public class OrderService {

    private static final String GET_PROD_URL = "http://product-service/products/{id}";

    @Autowired
    private RestTemplate restTemplate;

    public OrderResponse placeOrder(OrderRequest orderRequest) {

        // Step 1: Retrieve product details from product-service
        Product product = restTemplate.getForObject(GET_PROD_URL, Product.class, orderRequest.getProductId());

        if (product == null) {
            throw new RuntimeException("Product not found for ID: " + orderRequest.getProductId());
        }

        // Step 2: Process the order
        int quantity = orderRequest.getQuantity();
        double totalPrice = quantity * product.getPrice();
        String orderId = "ORD-" + new Random().nextInt(10000);

        // Step 3: Create and return response
        OrderResponse response = new OrderResponse();
        response.setOrderId(orderId);
        response.setProductId(product.getId());
        response.setProductName(product.getName());
        response.setQuantity(quantity);
        response.setTotalPrice(totalPrice);

        return response;
    }
}
