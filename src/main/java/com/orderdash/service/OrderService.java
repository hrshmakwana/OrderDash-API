package com.orderdash.service;

import com.orderdash.dto.CreateOrderRequest;
import com.orderdash.dto.OrderResponse;
import com.orderdash.model.Order;
import com.orderdash.model.Restaurant;
import com.orderdash.model.User;
import com.orderdash.repository.OrderRepository;
import com.orderdash.repository.RestaurantRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final RestaurantRepository restaurantRepository;

    public OrderService(OrderRepository orderRepository, RestaurantRepository restaurantRepository) {
        this.orderRepository = orderRepository;
        this.restaurantRepository = restaurantRepository;
    }

    public OrderResponse createOrder(CreateOrderRequest request, User currentUser) {
        Restaurant restaurant = restaurantRepository.findById(request.restaurantId())
                .orElseThrow(() -> new IllegalArgumentException("Restaurant not found with ID: " + request.restaurantId()));

        Order newOrder = new Order();
        newOrder.setUser(currentUser);
        newOrder.setRestaurant(restaurant);
        newOrder.setTotalAmount(request.totalAmount());

        Order savedOrder = orderRepository.save(newOrder);

        return new OrderResponse(
                savedOrder.getId(),
                savedOrder.getOrderDate(),
                savedOrder.getTotalAmount(),
                savedOrder.getRestaurant().getId(),
                savedOrder.getRestaurant().getName()
        );
    }

    public List<OrderResponse> getOrdersForUser(User user) {
        List<Order> orders = orderRepository.findByUser_Id(user.getId());

        return orders.stream()
                .map(order -> new OrderResponse(
                        order.getId(),
                        order.getOrderDate(),
                        order.getTotalAmount(),
                        order.getRestaurant().getId(),
                        order.getRestaurant().getName()
                ))
                .collect(Collectors.toList());
    }
}