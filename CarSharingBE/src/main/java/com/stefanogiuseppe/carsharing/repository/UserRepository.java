package com.stefanogiuseppe.carsharing.repository;

import com.stefanogiuseppe.carsharing.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserEntity, Long> {
}
