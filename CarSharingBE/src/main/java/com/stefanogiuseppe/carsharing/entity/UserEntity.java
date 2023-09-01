package com.stefanogiuseppe.carsharing.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Where;

import java.util.Date;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name="user")
@Where(clause = "deleted=0")
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="piva_or_cf")
    private String pivaOrCf;

    @Column(name="id_driving_license")
    private String idDrivingLicense;

    @Column(name="email")
    private String email;

    @Column(name="first_name")
    private String firstName;

    @Column(name="surname")
    private String surname;

    @Column(name="pwd")
    private String password;

    @Column(name="email_is_verified")
    private boolean emailIsVerified;

    @Column(name="url_profile_picture")
    private String urlProfilePicture;

    @Column(name = "residual_credit")
    private double residualCredit;

    @Column(name="deleted")
    @JsonIgnore
    private boolean deleted;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "idUser")
    //@JsonIgnore
    private List<RechargeEntity> recharges;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "idUser")
    @JsonIgnore
    private List<RentalEntity> rentals;
}
