package com.stefanogiuseppe.carsharing.repository;

import com.stefanogiuseppe.carsharing.entity.RechargeEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RechargeRepository extends JpaRepository<RechargeEntity, Long> {
}
