package com.norax.booktique_library.dao;

import com.norax.booktique_library.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewRepository extends JpaRepository<Review, Long> {

}
