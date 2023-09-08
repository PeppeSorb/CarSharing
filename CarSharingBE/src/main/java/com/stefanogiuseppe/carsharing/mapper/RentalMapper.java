package com.stefanogiuseppe.carsharing.mapper;

import com.stefanogiuseppe.carsharing.dto.RentalDTO;
import com.stefanogiuseppe.carsharing.dto.UserDTO;
import com.stefanogiuseppe.carsharing.dto.VehicleDTO;
import com.stefanogiuseppe.carsharing.entity.RentalEntity;
import com.stefanogiuseppe.carsharing.entity.UserEntity;
import com.stefanogiuseppe.carsharing.entity.VehicleEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RentalMapper {

    @Autowired
    VehicleMapper vehicleMapper=new VehicleMapper();

    @Autowired
    UserMapper userMapper= new UserMapper();

    public RentalDTO toDto(RentalEntity rentalEntity) {
        RentalDTO rentalDTO = new RentalDTO();
        rentalDTO.setId(rentalEntity.getId());

        UserEntity userEntity;
        if(rentalEntity.getIdUser()!=null){
            userEntity=rentalEntity.getIdUser();
            UserDTO userDTO=userMapper.toDTO(userEntity);
            rentalDTO.setIdUser(userDTO);
        }
        else
            rentalDTO.setIdUser(null);

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

        UserDTO userDTO;
        if(rentalDTO.getIdUser()!=null){
            userDTO=rentalDTO.getIdUser();
            UserEntity userEntity=userMapper.toEntity(userDTO);
            rentalEntity.setIdUser(userEntity);
        }
        else
            rentalEntity.setIdUser(null);

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
