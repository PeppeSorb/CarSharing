package com.stefanogiuseppe.carsharing.service;

import com.stefanogiuseppe.carsharing.dto.ModelDTO;
import com.stefanogiuseppe.carsharing.entity.ModelEntity;
import com.stefanogiuseppe.carsharing.repository.ModelRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.beans.FeatureDescriptor;
import java.util.List;
import java.util.stream.Stream;

@Service
public class ModelService {

    @Autowired
    private ModelRepository modelRepository;

    public ModelEntity saveModel(ModelEntity modelEntity) {
        return modelRepository.save(modelEntity);
    }

    public List<ModelEntity> getAllModel() {
        return modelRepository.findAll();
    }

    public ModelEntity findById(Long id) {
        return modelRepository.findById(id).orElseThrow();
    }

    public ModelEntity updateModel(Long id, ModelDTO modelDTO) {
        ModelEntity modelEntity = findById(id);
        modelDTO.setId(modelEntity.getId());

        BeanUtils.copyProperties(modelDTO, modelEntity, getNullPropertyNames(modelDTO));
        return modelRepository.save(modelEntity);

    }

    private String[] getNullPropertyNames(Object source) {
        final BeanWrapper src = new BeanWrapperImpl(source);
        return Stream.of(src.getPropertyDescriptors())
                .map(FeatureDescriptor::getName)
                .filter(propertyName -> src.getPropertyValue(propertyName) == null)
                .toArray(String[]::new);
    }

    public void deleteModel(Long id) {
        ModelEntity modelEntity = modelRepository.findById(id).orElseThrow();
        modelRepository.delete(modelEntity);
    }

}
