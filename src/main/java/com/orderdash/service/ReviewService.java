package com.orderdash.service;

import com.orderdash.dto.CreateReviewRequest;
import com.orderdash.dto.ReviewResponse;
import com.orderdash.model.Restaurant;
import com.orderdash.model.Review;
import com.orderdash.model.User;
import com.orderdash.repository.RestaurantRepository;
import com.orderdash.repository.ReviewRepository;
import org.springframework.stereotype.Service;

@Service
public class ReviewService {

    private final ReviewRepository reviewRepository;
    private final RestaurantRepository restaurantRepository;

    public ReviewService(ReviewRepository reviewRepository, RestaurantRepository restaurantRepository) {
        this.reviewRepository = reviewRepository;
        this.restaurantRepository = restaurantRepository;
    }

    public ReviewResponse createReview(Long restaurantId, CreateReviewRequest request, User currentUser) {
        Restaurant restaurant = restaurantRepository.findById(restaurantId)
                .orElseThrow(() -> new IllegalArgumentException("Restaurant not found with ID: " + restaurantId));

        Review newReview = new Review();
        newReview.setRating(request.rating());
        newReview.setComment(request.comment());
        newReview.setRestaurant(restaurant);
        newReview.setUser(currentUser);

        Review savedReview = reviewRepository.save(newReview);

        return new ReviewResponse(
                savedReview.getId(),
                savedReview.getRating(),
                savedReview.getComment(),
                savedReview.getReviewDate(),
                currentUser.getId(),
                currentUser.getName()
        );
    }
}