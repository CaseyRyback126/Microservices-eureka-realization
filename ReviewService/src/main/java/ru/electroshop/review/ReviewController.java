package ru.electroshop.review;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/reviews")
public class ReviewController {
    private final ReviewRepository reviewRepository;

    @Autowired
    public ReviewController(ReviewRepository reviewRepository) {
        this.reviewRepository = reviewRepository;
    }

    @GetMapping("/{id}")
    public List<Review> getReviewsByProductId(@PathVariable Long id) {
        return reviewRepository.findAllByProductId(id);
    }

    @PostMapping("/{id}")
    public Review addReview(@PathVariable Long id, @RequestBody Review review) {
        review.setProductId(id);
        return reviewRepository.save(review);
    }
}
