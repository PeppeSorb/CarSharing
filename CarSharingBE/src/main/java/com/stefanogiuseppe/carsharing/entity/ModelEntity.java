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
@Table(name = "model")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@ToString
@Data
//@Where(clause = "deleted = 0")
public class ModelEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id_category")
    @Column(name = "id_category")
    private Long idCategory;

    @Column(name = "make_and_model")
    private String makeAndModel;

    @Column(name = "boot_capacity")
    private int bootCapacity;

    @Column(name = "average_consumption")
    private double averageConsumption;

    @Column(name = "for_new_drivers")
    private boolean forNewDrivers;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "id_model")
    private List<VehicleEntity> vehicles;
}
