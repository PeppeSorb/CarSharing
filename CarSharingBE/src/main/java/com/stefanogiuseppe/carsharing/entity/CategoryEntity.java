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

public class CategoryEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_category")
    private Long id;


}

/*

public class VehicleEntity {
    @Column(name = "license_plate")
    private String licensePlate;

    @Column(name = "id_model")
    @ManyToOne
    @JoinColumn(name = "id")
    private int idModel;

    private String country;

    private String region;

    private String city;

    private String street;

    @Column(name = "house_number")
    private String houseNumber;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "id_vehicle")
    private List<RentalEntity> rentals;
}

 */
