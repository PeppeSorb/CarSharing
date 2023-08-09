package com.stefanogiuseppe.carsharing.repository;

import com.stefanogiuseppe.carsharing.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {
}
