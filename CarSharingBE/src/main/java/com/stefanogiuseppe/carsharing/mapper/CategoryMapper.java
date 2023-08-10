package com.stefanogiuseppe.carsharing.mapper;

import com.stefanogiuseppe.carsharing.dto.RechargeDTO;
import com.stefanogiuseppe.carsharing.dto.CategoryDTO;
import com.stefanogiuseppe.carsharing.entity.RechargeEntity;
import com.stefanogiuseppe.carsharing.entity.CategoryEntity;
import org.springframework.stereotype.Component;
@Component
public class CategoryMapper {
    public CategoryEntity toEntity(CategoryDTO categoryDTO) {
        CategoryEntity categoryEntity = new CategoryEntity();
        categoryEntity.setId(categoryDTO.getId());
        categoryEntity.setDailyRate(categoryDTO.getDailyRate());

        categoryEntity.setHourlyRate(categoryDTO.getHourlyRate());
        categoryEntity.setDailyRate(categoryDTO.getDailyRate());
        categoryEntity.setMonthlyRate(categoryDTO.getMonthlyRate());
        categoryEntity.setExtraHourlyRate(categoryDTO.getExtraHourlyRate());
        categoryEntity.setWeeklyRate(categoryDTO.getWeeklyRate());
        return categoryEntity;
    }
    public CategoryDTO toDto(CategoryEntity categoryEntity) {
        CategoryDTO categoryDTO = new CategoryDTO();
        categoryDTO.setId(categoryEntity.getId());
        categoryDTO.setDailyRate(categoryEntity.getDailyRate());

        categoryDTO.setHourlyRate(categoryEntity.getHourlyRate());
        categoryDTO.setDailyRate(categoryEntity.getDailyRate());
        categoryDTO.setMonthlyRate(categoryEntity.getMonthlyRate());
        categoryDTO.setExtraHourlyRate(categoryEntity.getExtraHourlyRate());
        categoryDTO.setWeeklyRate(categoryEntity.getWeeklyRate());
        return categoryDTO;
    }
}
