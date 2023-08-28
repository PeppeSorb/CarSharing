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
@Table(name = "administrator")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@ToString
@Data
//@Where(clause = "deleted = 0")
public class AdministratorEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String email;

    @Column(name = "first_name")
    private String firstName;

    private String surname;

    private String pwd;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "idAdmin")
    @JsonIgnore
    private List<RentalEntity> managedRentals;

    @Column(name="deleted")
    @JsonIgnore
    private Boolean deleted;
}
