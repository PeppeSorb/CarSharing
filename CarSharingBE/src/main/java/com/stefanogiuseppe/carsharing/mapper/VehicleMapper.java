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
        vehicleEntity.setIdMod(vehicleDTO.getIdModel());
        vehicleEntity.setLicensePlate(vehicleDTO.getLicensePlate());
        vehicleEntity.setStreet(vehicleDTO.getStreet());
        vehicleEntity.setBooked(vehicleDTO.getBooked());
        vehicleEntity.setLatitude(vehicleDTO.getLatitude());
        vehicleEntity.setLongitude(vehicleDTO.getLongitude());
        return vehicleEntity;
    }
    public VehicleDTO toDto(VehicleEntity vehicleEntity) {
        VehicleDTO vehicleDTO = new VehicleDTO();
        vehicleDTO.setId(vehicleEntity.getId());
        vehicleDTO.setCity(vehicleEntity.getCity());
        vehicleDTO.setCountry(vehicleEntity.getCountry());
        vehicleDTO.setRegion(vehicleEntity.getRegion());
        vehicleDTO.setHouseNumber(vehicleEntity.getHouseNumber());
        vehicleDTO.setIdModel(vehicleEntity.getIdMod());
        vehicleDTO.setLicensePlate(vehicleEntity.getLicensePlate());
        vehicleDTO.setStreet(vehicleEntity.getStreet());
        vehicleDTO.setBooked(vehicleEntity.getBooked());
        vehicleDTO.setLatitude(vehicleEntity.getLatitude());
        vehicleDTO.setLongitude(vehicleEntity.getLongitude());
        return vehicleDTO;
    }
}
