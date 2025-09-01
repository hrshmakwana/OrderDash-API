package com.orderdash.controller;

import com.orderdash.dto.CreateReviewRequest;
import com.orderdash.dto.ReviewResponse;
import com.orderdash.model.User;
import com.orderdash.service.ReviewService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import com.orderdash.model.Restaurant;
import com.orderdash.service.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/restaurants")
public class RestaurantController {
    private final RestaurantService restaurantService;
    private final ReviewService reviewService;

    @Autowired
    public RestaurantController(RestaurantService restaurantService,  ReviewService reviewService) {
        this.restaurantService = restaurantService;
        this.reviewService = reviewService;
    }

    @GetMapping
    public ResponseEntity<List<Restaurant>> getAllRestaurants( @RequestParam(required = false)  String cuisine) {
        List<Restaurant> restaurants = restaurantService.getAllRestaurants(cuisine);
        return ResponseEntity.ok(restaurants);
    }

    @PostMapping
    public ResponseEntity<Restaurant> createRestaurant(@RequestBody Restaurant restaurant) {
        Restaurant createdRestaurant = restaurantService.createRestaurant(restaurant);
        return new ResponseEntity<>(createdRestaurant, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Restaurant> getRestaurantById(@PathVariable Long id) {
        Optional<Restaurant> restaurantOptional = restaurantService.getRestaurantById(id);

        return restaurantOptional.map(restaurant -> ResponseEntity.ok(restaurant)).orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/{restaurantId}/reviews")
    public ResponseEntity<ReviewResponse> addReview(
            @PathVariable Long restaurantId,
            @RequestBody CreateReviewRequest request,
            @AuthenticationPrincipal User currentUser) {

        ReviewResponse reviewResponse = reviewService.createReview(restaurantId, request, currentUser);
        return new ResponseEntity<>(reviewResponse, HttpStatus.CREATED);
    }
}
