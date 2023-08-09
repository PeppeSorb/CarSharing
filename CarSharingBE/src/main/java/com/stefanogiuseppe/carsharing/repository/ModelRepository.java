package com.stefanogiuseppe.carsharing.repository;

import com.stefanogiuseppe.carsharing.entity.ModelEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ModelRepository extends JpaRepository<ModelEntity, Long> {

}
/*

 */
