package com.stefanogiuseppe.carsharing.dto;

import com.stefanogiuseppe.carsharing.entity.RentalEntity;
import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ReviewDTO {
    private Long id;
    private RentalEntity idRental;
    private Date dateTimeReview;
    private String textReview;
    private int valutation;
}
