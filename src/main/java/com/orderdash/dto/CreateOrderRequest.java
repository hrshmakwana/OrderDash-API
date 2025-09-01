package com.orderdash.dto;

import java.math.BigDecimal;

public record CreateOrderRequest(Long restaurantId, BigDecimal totalAmount) {
}