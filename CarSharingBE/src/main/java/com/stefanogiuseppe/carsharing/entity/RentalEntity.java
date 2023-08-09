package com.stefanogiuseppe.carsharing.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Where;

import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "rental")
@Where(clause = "deleted = 0")
public class RentalEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "id_user")
    private UserEntity idUser;

    //@Column(name = "id_vehicle")
    //private VehicleEntity id_vehicle;

    //@Column(name = "id_admin")
    //private AdministratorEntity id_admin;

    @Column(name = "date_time_start_rental")
    private Date dateTimeStartRental;

    @Column(name = "date_time_end_rental")
    private Date dateTimeEndRental;

    @Column(name = "type_rental")
    private String typeRental;
}