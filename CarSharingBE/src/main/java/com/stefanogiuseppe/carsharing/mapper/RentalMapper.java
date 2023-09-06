package com.stefanogiuseppe.carsharing.mapper;

import com.stefanogiuseppe.carsharing.dto.RentalDTO;
import com.stefanogiuseppe.carsharing.entity.RentalEntity;
import org.springframework.stereotype.Component;

@Component
public class RentalMapper {
    public RentalDTO toDto(RentalEntity rentalEntity) {
        RentalDTO rentalDTO = new RentalDTO();
        rentalDTO.setId(rentalEntity.getId());
        rentalDTO.setIdUser(rentalEntity.getIdUser());
        rentalDTO.setIdVehicle(rentalEntity.getIdVehicle());
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
        rentalEntity.setIdVehicle(rentalDTO.getIdVehicle());
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
