package com.stefanogiuseppe.carsharing.service;
import com.stefanogiuseppe.carsharing.dto.VehicleDTO;
import com.stefanogiuseppe.carsharing.entity.VehicleEntity;
import com.stefanogiuseppe.carsharing.repository.VehicleRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.beans.FeatureDescriptor;
import java.util.List;
import java.util.stream.Stream;

@Service
public class VehicleService {

    @Autowired
    private VehicleRepository vehicleRepository;

    public VehicleEntity saveVehicle(VehicleEntity vehicleEntity) {
        return vehicleRepository.save(vehicleEntity);
    }

    public List<VehicleEntity> getAllVehicle() {
        return vehicleRepository.findAll();
    }

    public VehicleEntity findById(Long id) {
        return vehicleRepository.findById(id).orElseThrow();
    }

    public VehicleEntity updateVehicle(Long id, VehicleDTO vehicleDTO) {
        VehicleEntity vehicleEntity = findById(id);
        vehicleDTO.setId(vehicleEntity.getId());

        BeanUtils.copyProperties(vehicleDTO, vehicleEntity, getNullPropertyNames(vehicleDTO));
        return vehicleRepository.save(vehicleEntity);

    }

    private String[] getNullPropertyNames(Object source) {
        final BeanWrapper src = new BeanWrapperImpl(source);
        return Stream.of(src.getPropertyDescriptors())
                .map(FeatureDescriptor::getName)
                .filter(propertyName -> src.getPropertyValue(propertyName) == null)
                .toArray(String[]::new);
    }

    public void deleteVehicle(Long id) {
        VehicleEntity vehicleEntity = vehicleRepository.findById(id).orElseThrow();
        vehicleRepository.delete(vehicleEntity);
    }

}