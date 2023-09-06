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
        modelEntity.setIdMod(modelDTO.getIdMod());
        modelEntity.setMakeAndModel(modelDTO.getMakeAndModel());
        modelEntity.setAverageConsumption(modelDTO.getAverageConsumption());

        modelEntity.setBootCapacity(modelDTO.getBootCapacity());

        modelEntity.setIdCategory(modelDTO.getIdCategory());
        modelEntity.setImage(modelDTO.getImage());
        return modelEntity;
    }
    public ModelDTO toDto(ModelEntity modelEntity) {
        ModelDTO modelDTO = new ModelDTO();
        modelDTO.setIdMod(modelEntity.getIdMod());
        modelDTO.setMakeAndModel(modelEntity.getMakeAndModel());
        modelDTO.setAverageConsumption(modelEntity.getAverageConsumption());

        modelDTO.setBootCapacity(modelEntity.getBootCapacity());

        modelDTO.setIdCategory(modelEntity.getIdCategory());
        modelDTO.setImage(modelEntity.getImage());
        return modelDTO;
    }
}
