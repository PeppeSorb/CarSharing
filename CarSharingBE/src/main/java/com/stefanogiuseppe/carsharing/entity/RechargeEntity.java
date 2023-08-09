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
@Table(name = "recharge")
@Where(clause = "deleted = 0")
public class RechargeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id_user")
    private UserEntity idUser;

    @Column(name="amount")
    private double amount;

    @Column(name="date_time")
    private LocalDateTime dateTime;

    @Column(name = "deleted")
    @JsonIgnore
    private Boolean deleted;
}
