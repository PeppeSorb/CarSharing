package com.stefanogiuseppe.carsharing.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Where;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "vehicle")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@ToString
@Data
//@Where(clause = "deleted = 0")
public class VehicleEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "license_plate")
    private String licensePlate;

    @ManyToOne
    @JoinColumn(name = "id_model")
    private ModelEntity idModel;

    @Column(name = "country")
    private String country;

    @Column(name = "region")
    private String region;

    @Column(name = "city")
    private String city;

    @Column(name = "street")
    private String street;

    @Column(name = "house_number")
    private String houseNumber;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "idVehicle")
    @JsonIgnore
    private List<RentalEntity> rentals;

    @Column(name="deleted")
    @JsonIgnore
    private Boolean deleted;
}
