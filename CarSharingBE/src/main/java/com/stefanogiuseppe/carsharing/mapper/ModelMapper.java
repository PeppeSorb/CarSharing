package com.stefanogiuseppe.carsharing.mapper;

import com.stefanogiuseppe.carsharing.dto.CategoryDTO;
import com.stefanogiuseppe.carsharing.dto.RechargeDTO;
import com.stefanogiuseppe.carsharing.dto.ModelDTO;
import com.stefanogiuseppe.carsharing.entity.CategoryEntity;
import com.stefanogiuseppe.carsharing.entity.RechargeEntity;
import com.stefanogiuseppe.carsharing.entity.ModelEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
@Component
public class ModelMapper {
    @Autowired
    private CategoryMapper categoryMapper;
    public ModelEntity toEntity(ModelDTO modelDTO) {
        ModelEntity modelEntity = new ModelEntity();
        modelEntity.setIdMod(modelDTO.getIdMod());
        modelEntity.setMakeAndModel(modelDTO.getMakeAndModel());
        modelEntity.setAverageConsumption(modelDTO.getAverageConsumption());

        modelEntity.setBootCapacity(modelDTO.getBootCapacity());
        modelEntity.setCategoryName(modelDTO.getCategoryName());
        /*
        CategoryDTO categoryDTO;

        if(modelDTO.getIdCategory()!=null){
            categoryDTO=modelDTO.getIdCategory();
            CategoryEntity categoryEntity = categoryMapper.toEntity(categoryDTO);
            modelEntity.setIdCategory(categoryEntity);
        }
        else
            modelEntity.setIdCategory(null);
        */
        modelEntity.setImage(modelDTO.getImage());
        return modelEntity;
    }
    public ModelDTO toDto(ModelEntity modelEntity) {
        ModelDTO modelDTO = new ModelDTO();
        modelDTO.setIdMod(modelEntity.getIdMod());
        modelDTO.setMakeAndModel(modelEntity.getMakeAndModel());
        modelDTO.setAverageConsumption(modelEntity.getAverageConsumption());

        modelDTO.setBootCapacity(modelEntity.getBootCapacity());
        modelDTO.setCategoryName(modelEntity.getCategoryName());
        /*
        CategoryEntity categoryEntity;
        if(modelEntity.getIdCategory()!=null){
            categoryEntity=modelEntity.getIdCategory();
            CategoryDTO categoryDTO=categoryMapper.toDto(categoryEntity);
            modelDTO.setIdCategory(categoryDTO);
        }
        else
            modelDTO.setIdCategory(null);
        */
        modelDTO.setImage(modelEntity.getImage());
        return modelDTO;
    }
}
