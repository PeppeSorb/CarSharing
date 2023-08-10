package com.stefanogiuseppe.carsharing.mapper;

import com.stefanogiuseppe.carsharing.dto.RechargeDTO;
import com.stefanogiuseppe.carsharing.dto.AdministratorDTO;
import com.stefanogiuseppe.carsharing.entity.RechargeEntity;
import com.stefanogiuseppe.carsharing.entity.AdministratorEntity;
import org.springframework.stereotype.Component;
@Component
public class AdministratorMapper {
    public AdministratorEntity toEntity(AdministratorDTO administratorDTO) {
        AdministratorEntity administratorEntity = new AdministratorEntity();
        administratorEntity.setId(administratorDTO.getId());
        administratorEntity.setEmail(administratorDTO.getEmail());
        administratorEntity.setPwd(administratorDTO.getPwd());
        administratorEntity.setFirstName(administratorDTO.getFirstName());
        administratorEntity.setSurname(administratorDTO.getSurname());
        return administratorEntity;
    }
    public AdministratorDTO toDto(AdministratorEntity administratorEntity) {
        AdministratorDTO administratorDTO = new AdministratorDTO();
        administratorDTO.setId(administratorEntity.getId());
        administratorDTO.setEmail(administratorEntity.getEmail());
        administratorDTO.setPwd(administratorEntity.getPwd());
        administratorDTO.setFirstName(administratorEntity.getFirstName());
        administratorDTO.setSurname(administratorEntity.getSurname());
        return administratorDTO;
    }
}
