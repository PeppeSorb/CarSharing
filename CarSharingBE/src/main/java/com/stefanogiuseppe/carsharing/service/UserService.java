package com.stefanogiuseppe.carsharing.service;

import com.stefanogiuseppe.carsharing.dto.RentalDTO;
import com.stefanogiuseppe.carsharing.dto.UserDTO;
import com.stefanogiuseppe.carsharing.entity.RentalEntity;
import com.stefanogiuseppe.carsharing.entity.UserEntity;
import com.stefanogiuseppe.carsharing.repository.RentalRepository;
import com.stefanogiuseppe.carsharing.repository.UserRepository;
import jakarta.mail.MessagingException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.beans.FeatureDescriptor;
import java.util.List;
import java.util.stream.Stream;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private EmailService emailService;

    public UserEntity saveUser(UserEntity userEntity) {
                userRepository.save(userEntity);
                try {
                    String verifyLink = "http://localhost:8080/api/user/verify/" + userEntity.getId();
                    emailService.sendVerificationEmail(userEntity.getEmail(), verifyLink);
                }catch (MessagingException e){e.printStackTrace();}
          return userEntity;
    }

    public List<UserEntity> getAllUser() {
        return userRepository.findAll();
    }

    public UserEntity findById(Long id) {
        return userRepository.findById(id).orElseThrow();
    }

    public UserEntity updateUser(Long id, UserDTO userDTO) {
        UserEntity userEntity = findById(id);
        userDTO.setId(userEntity.getId());

        BeanUtils.copyProperties(userDTO, userEntity, getNullPropertyNames(userDTO));
        return userRepository.save(userEntity);

    }

    private String[] getNullPropertyNames(Object source) {
        final BeanWrapper src = new BeanWrapperImpl(source);
        return Stream.of(src.getPropertyDescriptors())
                .map(FeatureDescriptor::getName)
                .filter(propertyName -> src.getPropertyValue(propertyName) == null)
                .toArray(String[]::new);
    }

    public void deleteUser(Long id) {
        UserEntity userEntity = userRepository.findById(id).orElseThrow();
        userEntity.setDeleted(true);
        userRepository.save(userEntity);
    }
}
