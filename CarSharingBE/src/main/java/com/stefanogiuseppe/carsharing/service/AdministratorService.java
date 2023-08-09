package com.stefanogiuseppe.carsharing.service;

import com.stefanogiuseppe.carsharing.dto.AdministratorDTO;
import com.stefanogiuseppe.carsharing.entity.AdministratorEntity;
import com.stefanogiuseppe.carsharing.repository.AdministratorRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.beans.FeatureDescriptor;
import java.util.List;
import java.util.stream.Stream;

@Service
public class AdministratorService {

    @Autowired
    private AdministratorRepository administratorRepository;

    public AdministratorEntity saveAdministrator(AdministratorEntity administratorEntity) {
        return administratorRepository.save(administratorEntity);
    }

    public List<AdministratorEntity> getAllAdministrator() {
        return administratorRepository.findAll();
    }

    public AdministratorEntity findById(Long id) {
        return administratorRepository.findById(id).orElseThrow();
    }

    public AdministratorEntity updateAdministrator(Long id, AdministratorDTO administratorDTO) {
        AdministratorEntity administratorEntity = findById(id);
        administratorDTO.setId(administratorEntity.getId());

        BeanUtils.copyProperties(administratorDTO, administratorEntity, getNullPropertyNames(administratorDTO));
        return administratorRepository.save(administratorEntity);

    }

    private String[] getNullPropertyNames(Object source) {
        final BeanWrapper src = new BeanWrapperImpl(source);
        return Stream.of(src.getPropertyDescriptors())
                .map(FeatureDescriptor::getName)
                .filter(propertyName -> src.getPropertyValue(propertyName) == null)
                .toArray(String[]::new);
    }

    public void deleteAdministrator(Long id) {
        AdministratorEntity administratorEntity = administratorRepository.findById(id).orElseThrow();
        administratorRepository.delete(administratorEntity);
    }

}
