package com.stefanogiuseppe.carsharing.repository;

import com.stefanogiuseppe.carsharing.entity.ReviewEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReviewRepository extends JpaRepository<ReviewEntity, Long> {
}
