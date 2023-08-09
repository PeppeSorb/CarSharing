package com.stefanogiuseppe.carsharing.dto;
import com.stefanogiuseppe.carsharing.entity.ModelEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class CategoryDTO {
    private Long id;
    private double hourlyRate;
    private double dailyRate;
    private double twoDaysRate;
    private double weeklyRate;
    private double monthlyRate;
    private double extraHourlyRate;
    private List<ModelEntity> models;
}
