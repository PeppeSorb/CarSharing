package com.stefanogiuseppe.carsharing.dto;

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
public class AdministratorDTO {
    private Long id;
    private String email;
    private String firstName;
    private String surname;
    private String pwd;
    private List<RentalEntity> managedRentals;

}
