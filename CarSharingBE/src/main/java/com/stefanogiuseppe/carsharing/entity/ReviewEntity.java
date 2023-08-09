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
public class ReviewEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "id_rental")
    private RentalEntity idRental;

    @Column(name = "date_time_review")
    private Date dateTimeReview;

    @Column(name = "text_review")
    private String textReview;

    @Column(name = "valutation")
    private int valutation;
}
