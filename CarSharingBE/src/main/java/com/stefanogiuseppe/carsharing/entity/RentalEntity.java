package com.stefanogiuseppe.carsharing.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
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

    @ManyToOne
    @JoinColumn(name = "id_user")
    private UserEntity idUser;

    @ManyToOne
    @JoinColumn(name = "id_vehicle")
    private VehicleEntity idVehicle;

    @OneToOne
    @JoinColumn(name = "id_admin")
    private AdministratorEntity idAdmin;

    @Column(name = "date_time_start_rental")
    private Date dateTimeStartRental;

    @Column(name = "date_time_end_rental")
    private Date dateTimeEndRental;

    @Column(name = "type_rental")
    private String typeRental;

    @Column(name = "payed")
    private Boolean payed;

    @Column(name = "deleted")
    @JsonIgnore
    private Boolean deleted;

    @OneToOne(mappedBy = "idRental")
    @JsonIgnore
    private ReviewEntity review;

}

