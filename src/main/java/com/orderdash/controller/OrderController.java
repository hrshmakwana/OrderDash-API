package com.orderdash.controller;

import com.orderdash.dto.CreateOrderRequest;
import com.orderdash.dto.OrderResponse;
import com.orderdash.model.User;
import com.orderdash.service.OrderService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/orders")
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping
    public ResponseEntity<OrderResponse> createOrder(
            @RequestBody CreateOrderRequest request,
            @AuthenticationPrincipal User currentUser) {

        OrderResponse createdOrderResponse = orderService.createOrder(request, currentUser);
        return new ResponseEntity<>(createdOrderResponse, HttpStatus.CREATED);
    }

    @GetMapping("/my-orders")
    public ResponseEntity<List<OrderResponse>> getMyOrders(@AuthenticationPrincipal User currentUser) {
        List<OrderResponse> orders = orderService.getOrdersForUser(currentUser);
        return ResponseEntity.ok(orders);
    }
}