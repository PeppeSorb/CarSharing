package com.stefanogiuseppe.carsharing.service;

import com.stefanogiuseppe.carsharing.dto.RechargeDTO;
import com.stefanogiuseppe.carsharing.dto.UserDTO;
import com.stefanogiuseppe.carsharing.entity.RechargeEntity;
import com.stefanogiuseppe.carsharing.entity.UserEntity;
import com.stefanogiuseppe.carsharing.repository.RechargeRepository;
import com.stefanogiuseppe.carsharing.repository.UserRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.beans.FeatureDescriptor;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

@Service
public class RechargeService {
    @Autowired
    private RechargeRepository rechargeRepository;

    @Autowired
    private UserRepository userRepository;

    public RechargeEntity saveRecharge(RechargeEntity rechargeEntity) {
        return rechargeRepository.save(rechargeEntity);
    }

    public RechargeEntity addRechargeAndAddCreditToUser(RechargeEntity rechargeEntity){
        Optional<UserEntity> user = userRepository.findById(rechargeEntity.getIdUser().getId());
        if(user.isEmpty() == false){
            user.get().setResidualCredit(user.get().getResidualCredit() + rechargeEntity.getAmount());
            return rechargeRepository.save(rechargeEntity);
        }else{
            return null;
        }

    }

    public List<RechargeEntity> getAllRecharge() {
        return rechargeRepository.findAll();
    }

    public RechargeEntity findById(Long id) {
        return rechargeRepository.findById(id).orElseThrow();
    }

    public RechargeEntity updateRecharge(Long id, RechargeDTO rechargeDTO) {
        RechargeEntity rechargeEntity = findById(id);
        rechargeDTO.setId(rechargeEntity.getId());

        BeanUtils.copyProperties(rechargeDTO, rechargeEntity, getNullPropertyNames(rechargeDTO));
        return rechargeRepository.save(rechargeEntity);

    }

    private String[] getNullPropertyNames(Object source) {
        final BeanWrapper src = new BeanWrapperImpl(source);
        return Stream.of(src.getPropertyDescriptors())
                .map(FeatureDescriptor::getName)
                .filter(propertyName -> src.getPropertyValue(propertyName) == null)
                .toArray(String[]::new);
    }

    public void deleteRecharge(Long id) {
        RechargeEntity rechargeEntity = rechargeRepository.findById(id).orElseThrow();
        rechargeEntity.setDeleted(true);
        rechargeRepository.save(rechargeEntity);
    }
}
