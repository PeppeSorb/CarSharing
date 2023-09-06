package com.stefanogiuseppe.carsharing.dto;

import com.stefanogiuseppe.carsharing.entity.CategoryEntity;
import com.stefanogiuseppe.carsharing.entity.VehicleEntity;
import jakarta.persistence.*;

import java.util.List;
import com.stefanogiuseppe.carsharing.entity.ModelEntity;
import com.stefanogiuseppe.carsharing.entity.RentalEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ModelDTO {
    private Long idMod;
    private CategoryEntity idCategory;
    private String makeAndModel;
    private int bootCapacity;
    private double averageConsumption;
    private boolean forNewDrivers;
    private String image;
    //private List<VehicleEntity> vehicles;
}

