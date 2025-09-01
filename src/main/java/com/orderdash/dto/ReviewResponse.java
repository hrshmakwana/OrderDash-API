package com.orderdash.dto;

import java.time.LocalDateTime;

public record ReviewResponse(
        Long reviewId,
        int rating,
        String comment,
        LocalDateTime reviewDate,
        Long userId,
        String userName
) {
}