package com.stefanogiuseppe.carsharing.mapper;

import com.stefanogiuseppe.carsharing.dto.RechargeDTO;
import com.stefanogiuseppe.carsharing.entity.RechargeEntity;
import org.springframework.stereotype.Component;

@Component
public class RechargeMapper {
    public RechargeEntity convertDtoToEntity(RechargeDTO rechargeDTO) {
        RechargeEntity rechargeEntity = new RechargeEntity();
        rechargeEntity.setId(rechargeDTO.getId());
        rechargeEntity.setIdUser(rechargeDTO.getIdUser());
        rechargeEntity.setAmount(rechargeDTO.getAmount());
        rechargeEntity.setDateTime(rechargeDTO.getDateTime());
        return rechargeEntity;
    }

    public RechargeDTO convertEntityToDto(RechargeEntity rechargeEntity) {
        RechargeDTO rechargeDTO = new RechargeDTO();
        rechargeDTO.setId(rechargeEntity.getId());
        rechargeDTO.setIdUser(rechargeEntity.getIdUser());
        rechargeDTO.setAmount(rechargeEntity.getAmount());
        rechargeDTO.setDateTime(rechargeEntity.getDateTime());
        return rechargeDTO;
    }
}
