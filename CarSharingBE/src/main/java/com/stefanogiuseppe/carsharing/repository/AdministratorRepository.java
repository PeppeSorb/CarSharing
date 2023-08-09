package com.stefanogiuseppe.carsharing.repository;

import com.stefanogiuseppe.carsharing.entity.AdministratorEntity;
import com.stefanogiuseppe.carsharing.entity.ModelEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdministratorRepository extends JpaRepository<AdministratorEntity, Long> {

}
