package com.stefanogiuseppe.carsharing.dto;
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
public class VehicleDTO {
    private Long id;
    private String licensePlate;
    private ModelEntity idModel;
    private String country;
    private String region;
    private String city;
    private String street;
    private String houseNumber;
    private Boolean booked;
    //private List<RentalEntity> rentals;
}
