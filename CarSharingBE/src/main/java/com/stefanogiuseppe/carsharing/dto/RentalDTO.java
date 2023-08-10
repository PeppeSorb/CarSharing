package com.stefanogiuseppe.carsharing.dto;

import com.stefanogiuseppe.carsharing.entity.AdministratorEntity;
import com.stefanogiuseppe.carsharing.entity.ReviewEntity;
import com.stefanogiuseppe.carsharing.entity.UserEntity;
import com.stefanogiuseppe.carsharing.entity.VehicleEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class RentalDTO {
    private Long id;
    private UserEntity idUser;
    private VehicleEntity id_vehicle;
    private AdministratorEntity id_admin;
    private Date dateTimeStartRental;
    private Date dateTimeEndRental;
    private String typeRental;
    private ReviewEntity review;
}
