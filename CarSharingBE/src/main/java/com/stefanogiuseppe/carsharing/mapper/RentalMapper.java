package com.stefanogiuseppe.carsharing.mapper;

import com.stefanogiuseppe.carsharing.dto.ModelDTO;
import com.stefanogiuseppe.carsharing.dto.RentalDTO;
import com.stefanogiuseppe.carsharing.dto.VehicleDTO;
import com.stefanogiuseppe.carsharing.entity.ModelEntity;
import com.stefanogiuseppe.carsharing.entity.RentalEntity;
import com.stefanogiuseppe.carsharing.entity.VehicleEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RentalMapper {

    @Autowired
    VehicleMapper vehicleMapper=new VehicleMapper();

    public RentalDTO toDto(RentalEntity rentalEntity) {
        RentalDTO rentalDTO = new RentalDTO();
        rentalDTO.setId(rentalEntity.getId());
        rentalDTO.setIdUser(rentalEntity.getIdUser());

        VehicleEntity vehicleEntity;
        if(rentalEntity.getIdVehicle()!=null){
            vehicleEntity=rentalEntity.getIdVehicle();
            VehicleDTO vehicleDTO=vehicleMapper.toDto(vehicleEntity);
            rentalDTO.setIdVehicle(vehicleDTO);
        }
        else
            rentalDTO.setIdVehicle(null);
        
     //   rentalDTO.setIdAdmin(rentalEntity.getIdAdmin());
        rentalDTO.setDateTimeStartRental(rentalEntity.getDateTimeStartRental());
        rentalDTO.setDateTimeEndRental(rentalEntity.getDateTimeEndRental());
        rentalDTO.setTypeRental(rentalEntity.getTypeRental());
        rentalDTO.setPayed(rentalEntity.getPayed());
        rentalDTO.setPrice(rentalEntity.getPrice());
        rentalDTO.setExtraPay(rentalEntity.getExtraPay());
        return rentalDTO;
    }

    public RentalEntity toEntity(RentalDTO rentalDTO) {
        RentalEntity rentalEntity = new RentalEntity();
        rentalEntity.setId(rentalDTO.getId());
        rentalEntity.setIdUser(rentalDTO.getIdUser());

        VehicleDTO vehicleDTO;
        if(rentalDTO.getIdVehicle()!=null){
            vehicleDTO=rentalDTO.getIdVehicle();
            VehicleEntity vehicleEntity=vehicleMapper.toEntity(vehicleDTO);
            rentalEntity.setIdVehicle(vehicleEntity);
        }
        else
            rentalEntity.setIdVehicle(null);
      //  rentalEntity.setIdAdmin(rentalDTO.getIdAdmin());
        rentalEntity.setDateTimeStartRental(rentalDTO.getDateTimeStartRental());
        rentalEntity.setDateTimeEndRental(rentalDTO.getDateTimeEndRental());
        rentalEntity.setTypeRental(rentalDTO.getTypeRental());
        rentalEntity.setPayed(rentalDTO.getPayed());
        rentalEntity.setPrice(rentalDTO.getPrice());
        rentalEntity.setExtraPay(rentalDTO.getExtraPay());
        return rentalEntity;
    }
}
