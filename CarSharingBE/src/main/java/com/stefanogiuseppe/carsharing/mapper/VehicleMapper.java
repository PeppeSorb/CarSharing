package com.stefanogiuseppe.carsharing.mapper;

import com.stefanogiuseppe.carsharing.dto.RechargeDTO;
import com.stefanogiuseppe.carsharing.dto.VehicleDTO;
import com.stefanogiuseppe.carsharing.entity.RechargeEntity;
import com.stefanogiuseppe.carsharing.entity.VehicleEntity;
import org.springframework.stereotype.Component;
@Component
public class VehicleMapper {
    public VehicleEntity toEntity(VehicleDTO vehicleDTO) {
        VehicleEntity vehicleEntity = new VehicleEntity();
        vehicleEntity.setId(vehicleDTO.getId());
        vehicleEntity.setCity(vehicleDTO.getCity());
        vehicleEntity.setCountry(vehicleDTO.getCountry());
        vehicleEntity.setRegion(vehicleDTO.getRegion());
        vehicleEntity.setHouseNumber(vehicleDTO.getHouseNumber());
        vehicleEntity.setIdModel(vehicleDTO.getIdModel());
        vehicleEntity.setLicensePlate(vehicleDTO.getLicensePlate());
        vehicleEntity.setStreet(vehicleDTO.getStreet());
        return vehicleEntity;
    }
    public VehicleDTO toDto(VehicleEntity vehicleEntity) {
        VehicleDTO vehicleDTO = new VehicleDTO();
        vehicleDTO.setId(vehicleDTO.getId());
        vehicleDTO.setCity(vehicleDTO.getCity());
        vehicleDTO.setCountry(vehicleDTO.getCountry());
        vehicleDTO.setRegion(vehicleDTO.getRegion());
        vehicleDTO.setHouseNumber(vehicleDTO.getHouseNumber());
        vehicleDTO.setIdModel(vehicleDTO.getIdModel());
        vehicleDTO.setLicensePlate(vehicleDTO.getLicensePlate());
        vehicleDTO.setStreet(vehicleDTO.getStreet());
        return vehicleDTO;
    }
}
