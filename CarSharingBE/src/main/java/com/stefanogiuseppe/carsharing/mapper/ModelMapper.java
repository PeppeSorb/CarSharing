package com.stefanogiuseppe.carsharing.mapper;

import com.stefanogiuseppe.carsharing.dto.RechargeDTO;
import com.stefanogiuseppe.carsharing.dto.ModelDTO;
import com.stefanogiuseppe.carsharing.entity.RechargeEntity;
import com.stefanogiuseppe.carsharing.entity.ModelEntity;
import org.springframework.stereotype.Component;
@Component
public class ModelMapper {
    public ModelEntity toEntity(ModelDTO modelDTO) {
        ModelEntity modelEntity = new ModelEntity();
        modelEntity.setId(modelDTO.getId());
        modelEntity.setMakeAndModel(modelDTO.getMakeAndModel());
        modelEntity.setAverageConsumption(modelDTO.getAverageConsumption());

        modelEntity.setBootCapacity(modelDTO.getBootCapacity());

        modelEntity.setIdCategory(modelDTO.getIdCategory());
        return modelEntity;
    }
    public ModelDTO toDto(ModelEntity modelEntity) {
        ModelDTO modelDTO = new ModelDTO();
        modelDTO.setId(modelEntity.getId());
        modelDTO.setMakeAndModel(modelEntity.getMakeAndModel());
        modelDTO.setAverageConsumption(modelEntity.getAverageConsumption());

        modelDTO.setBootCapacity(modelEntity.getBootCapacity());

        modelDTO.setIdCategory(modelEntity.getIdCategory());
        return modelDTO;
    }
}
