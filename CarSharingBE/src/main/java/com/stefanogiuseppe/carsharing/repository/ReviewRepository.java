package com.stefanogiuseppe.carsharing.repository;

import com.stefanogiuseppe.carsharing.entity.ReviewEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewRepository extends JpaRepository<ReviewEntity, Long> {
}
