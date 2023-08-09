package com.stefanogiuseppe.carsharing.service;

import com.stefanogiuseppe.carsharing.dto.RentalDTO;
import com.stefanogiuseppe.carsharing.entity.RentalEntity;
import com.stefanogiuseppe.carsharing.repository.RentalRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.beans.FeatureDescriptor;
import java.util.List;
import java.util.stream.Stream;

@Service
public class RentalService {
    @Autowired
    private RentalRepository rentalRepository;

    public RentalEntity saveRental(RentalEntity rentalEntity) {
        return rentalRepository.save(rentalEntity);
    }

    public List<RentalEntity> getAllRental() {
        return rentalRepository.findAll();
    }

    public RentalEntity findById(Long id) {
        return rentalRepository.findById(id).orElseThrow();
    }

    public RentalEntity updateRental(Long id, RentalDTO rentalDTO) {
        RentalEntity rentalEntity = findById(id);
        rentalDTO.setId(rentalEntity.getId());

        BeanUtils.copyProperties(rentalDTO, rentalEntity, getNullPropertyNames(rentalDTO));
        return rentalRepository.save(rentalEntity);

    }

    private String[] getNullPropertyNames(Object source) {
        final BeanWrapper src = new BeanWrapperImpl(source);
        return Stream.of(src.getPropertyDescriptors())
                .map(FeatureDescriptor::getName)
                .filter(propertyName -> src.getPropertyValue(propertyName) == null)
                .toArray(String[]::new);
    }

    public void deleteRental(Long id) {
        RentalEntity rentalEntity = rentalRepository.findById(id).orElseThrow();
        rentalEntity.setDeleted(true);
        rentalRepository.save(rentalEntity);
    }

}
