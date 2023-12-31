package com.stefanogiuseppe.carsharing.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Where;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "category")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@ToString
@Data
@Where(clause = "deleted = 0")

public class CategoryEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "category_name")
    private String categoryName;

    @Column(name = "hourly_rate")
    private double hourlyRate;

    @Column(name = "daily_rate")
    private double dailyRate;

    @Column(name = "two_days_rate")
    private double twoDaysRate;

    @Column(name = "weekly_rate")
    private double weeklyRate;

    @Column(name = "monthly_rate")
    private double monthlyRate;

    @Column(name = "extra_hourly_rate")
    private double extraHourlyRate;

    @Column(name = "valid_from")
    private Date validFrom;

    @Column(name = "valid_to")
    private Date validTo;


    @Column(name="deleted")
    @JsonIgnore
    private Boolean deleted;

}

