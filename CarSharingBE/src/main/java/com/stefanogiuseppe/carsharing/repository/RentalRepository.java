package com.stefanogiuseppe.carsharing.repository;

import com.stefanogiuseppe.carsharing.entity.RentalEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RentalRepository extends JpaRepository<RentalEntity, Long> {
}
