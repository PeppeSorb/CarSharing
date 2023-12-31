package com.stefanogiuseppe.carsharing.dto;

import com.stefanogiuseppe.carsharing.entity.UserEntity;
import com.stefanogiuseppe.carsharing.entity.VehicleEntity;
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
    private UserDTO idUser;
    private VehicleDTO idVehicle;
   // private AdministratorEntity idAdmin;
    private Date dateTimeStartRental;
    private Date dateTimeEndRental;
    private String typeRental;
    private Boolean payed;
    private Double price;
    private Boolean extraPay;
}
