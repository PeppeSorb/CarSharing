package com.stefanogiuseppe.carsharing.repository;
import com.stefanogiuseppe.carsharing.entity.VehicleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface VehicleRepository extends JpaRepository<VehicleEntity, Long> {
}

/*
package newodoo.repository;

import newodoo.entity.SubProjectEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SubProjectRepository extends JpaRepository<SubProjectEntity, Long> {
}
 */
