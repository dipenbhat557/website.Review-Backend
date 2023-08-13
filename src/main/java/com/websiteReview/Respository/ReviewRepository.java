package com.websiteReview.Respository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.websiteReview.Model.Review;
import com.websiteReview.Model.User;

public interface ReviewRepository extends JpaRepository<Review, Integer> {

    public Page<Review> findByUser(User user, Pageable pageable);

    @Query("SELECT r FROM Review r WHERE r.aboutReviewUser.organizationSize >= :minSize AND r.aboutReviewUser.organizationSize <= :maxSize")
    public Page<Review> findByOrganizationSizeRange(int minSize, int maxSize, Pageable pageable);

    @Query("SELECT r FROM Review r WHERE r.rating >= :minRating AND r.rating < :maxRating")
    public Page<Review> findByRatingRange(int minRating, int maxRating, Pageable pageable);

    @Query("SELECT r FROM Review r WHERE LOWER(r.aboutReviewProduct.purposeOfUse) = LOWER(:purpose)")
    public Page<Review> findByPurposeOfUseIgnoreCase(String purpose, Pageable pageable);

    public Page<Review> findByUserRole(String userRole, Pageable pageable);
}
