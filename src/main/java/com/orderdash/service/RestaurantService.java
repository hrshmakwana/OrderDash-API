package com.orderdash.service;

import com.orderdash.model.Restaurant;
import com.orderdash.repository.RestaurantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class RestaurantService {
    private final RestaurantRepository restaurantRepository;

    @Autowired
    public RestaurantService(RestaurantRepository restaurantRepository) {
        this.restaurantRepository = restaurantRepository;
    }

    public Restaurant createRestaurant(Restaurant restaurant) {
        return restaurantRepository.save(restaurant);
    }
    public List<Restaurant> getAllRestaurants(String cuisine) {
        if (cuisine != null && !cuisine.isBlank()) {
            return restaurantRepository.findByCuisineIgnoreCase(cuisine);
        } else {
            return restaurantRepository.findAll();
        }
    }
    public Optional<Restaurant> getRestaurantById(Long id) {
        return restaurantRepository.findById(id);
    }
}
