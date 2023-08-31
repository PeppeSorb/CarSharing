package com.stefanogiuseppe.carsharing.service;

import com.stefanogiuseppe.carsharing.dto.RentalDTO;
import com.stefanogiuseppe.carsharing.dto.UserDTO;
import com.stefanogiuseppe.carsharing.entity.CategoryEntity;
import com.stefanogiuseppe.carsharing.entity.RechargeEntity;
import com.stefanogiuseppe.carsharing.entity.RentalEntity;
import com.stefanogiuseppe.carsharing.entity.UserEntity;
import com.stefanogiuseppe.carsharing.repository.RechargeRepository;
import com.stefanogiuseppe.carsharing.repository.RentalRepository;
import com.stefanogiuseppe.carsharing.repository.UserRepository;
import jakarta.mail.MessagingException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.beans.FeatureDescriptor;
import java.util.Date;
import java.util.List;
import java.util.stream.Stream;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RechargeRepository rechargeRepository;

    @Autowired
    private RentalRepository rentalRepository;

    @Autowired
    private EmailService emailService;

    public UserEntity saveUser(UserEntity userEntity) {
        List<UserEntity> userEntities = userRepository.findAll();
        for (UserEntity user : userEntities) {
            if (userEntity.getId() == user.getId()) {
                userRepository.save(userEntity);
                return userEntity;
            }
        }

        userRepository.save(userEntity);
        try {
            String verifyLink = "http://localhost:8080/api/user/verify/" + userEntity.getId();
            emailService.sendVerificationEmail(userEntity.getEmail(), verifyLink);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
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
    public double getUserResidualCredit(Long userId){
        double credit = 0;
        //somma tutte le ricariche
        List<RechargeEntity> recharges = rechargeRepository.findAll();
        for(RechargeEntity re : recharges){
            if(re.getIdUser().getId() == userId){
                credit = credit + re.getAmount();
            }
        }
        //sottrai tutti i costi dei noleggi già conclusi
        List<RentalEntity> rentals = rentalRepository.findAll();
        for(RentalEntity r : rentals){
            if(r.getDateTimeEndRental() != null && r.getIdUser().getId() == userId){
                //r è un noleggio concluso dell'utente con id userId
                //cerca di capire quanto ha pagato l'utente
                //cosa determina il prezzo da pagare:
                //category del veicolo
                //tempo totale di utilizzo
                //tipo di noleggio
                //considera eventuali extra
                //indispensabile ottenere il tempo trascorso tra datetime start e datetime end
                CategoryEntity ce = r.getIdVehicle().getIdModel().getIdCategory();
                r.getDateTimeEndRental();
                Date diff = r.getDateTimeEndRental(); //continuare calcolando la differenza

            }
        }
        return credit;
    }
}
