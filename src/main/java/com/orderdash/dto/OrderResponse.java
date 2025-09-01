package com.orderdash.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record OrderResponse(
        Long orderId,
        LocalDateTime orderDate,
        BigDecimal totalAmount,
        Long restaurantId,
        String restaurantName
) {
}