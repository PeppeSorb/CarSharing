package com.stefanogiuseppe.carsharing.mapper;

import com.stefanogiuseppe.carsharing.dto.UserDTO;
import com.stefanogiuseppe.carsharing.entity.UserEntity;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {
    public UserDTO toDTO(UserEntity userEntity) {
        UserDTO userDTO = new UserDTO();
        userDTO.setId(userEntity.getId());
        userDTO.setPivaOrCf(userEntity.getPivaOrCf());
        userDTO.setIdDrivingLicense(userEntity.getIdDrivingLicense());
        userDTO.setEmail(userEntity.getEmail());
        userDTO.setFirstName(userEntity.getFirstName());
        userDTO.setSurname(userEntity.getSurname());
        userDTO.setPassword(userEntity.getPassword());
        userDTO.setEmailIsVerified(userEntity.isEmailIsVerified());
        userDTO.setUrlProfilePicture(userEntity.getUrlProfilePicture());
        return userDTO;
    }

    public static UserEntity toEntity(UserDTO userDTO) {
        UserEntity userEntity = new UserEntity();
        userEntity.setId(userDTO.getId());
        userEntity.setPivaOrCf(userDTO.getPivaOrCf());
        userEntity.setIdDrivingLicense(userDTO.getIdDrivingLicense());
        userEntity.setEmail(userDTO.getEmail());
        userEntity.setFirstName(userDTO.getFirstName());
        userEntity.setSurname(userDTO.getSurname());
        userEntity.setPassword(userDTO.getPassword());
        userEntity.setEmailIsVerified(userDTO.isEmailIsVerified());
        userEntity.setUrlProfilePicture(userDTO.getUrlProfilePicture());
        return userEntity;
    }
}
