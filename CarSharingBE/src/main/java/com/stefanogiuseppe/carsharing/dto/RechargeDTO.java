package com.stefanogiuseppe.carsharing.dto;

import com.stefanogiuseppe.carsharing.entity.UserEntity;
import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class RechargeDTO {
    private Long id;
    //private UserEntity idUser;
    private double amount;
    private LocalDateTime dateTime;
}
