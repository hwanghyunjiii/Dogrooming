package com.h2.dogrooming.review;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ReviewService {

    @Autowired
    ReviewRepository reviewRepository;

    // 리뷰 등록
    public void registerReview(Review review) {
        reviewRepository.save(review);
    }
}
