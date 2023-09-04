package com.stefanogiuseppe.carsharing.service;

import com.stefanogiuseppe.carsharing.dto.RentalDTO;
import com.stefanogiuseppe.carsharing.dto.UserDTO;
import com.stefanogiuseppe.carsharing.entity.*;
import com.stefanogiuseppe.carsharing.repository.RechargeRepository;
import com.stefanogiuseppe.carsharing.repository.RentalRepository;
import com.stefanogiuseppe.carsharing.repository.UserRepository;
import jakarta.mail.MessagingException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.beans.FeatureDescriptor;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.stream.Stream;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RentalService rentalService;

    @Autowired
    private RechargeRepository rechargeRepository;

    @Value("${users.file.upload.dir}") // Configura questa property nel tuo application.properties o application.yml
    private String uploadDir; // Percorso della cartella di caricamento delle immagini

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
    public String uploadImageToUser(Long userId, MultipartFile file){
        try {
            UserEntity user = findById(userId);
            if (user == null) {
                return "User not found";
            }
            String fileName = UUID.randomUUID().toString() + ".png";
            String filePath = Paths.get(uploadDir, fileName).toString();
            Files.copy(file.getInputStream(), Paths.get(filePath), StandardCopyOption.REPLACE_EXISTING);
            String imageUrl = fileName;
            user.setUrlProfilePicture(imageUrl);
            saveUser(user);
            return imageUrl;
        } catch (IOException e) {
            e.printStackTrace();
            return "Bad request";
        }
    }
    public void deleteUnusedUserImages(){
        File directory = new File("src/main/resources/static/users_images");
        File[] files = directory.listFiles();
        List<String> usedImageUrls = getAllUsedImageUrls();
        for(String str : usedImageUrls){
            System.out.println(str);
        }
        for (File file : files) {
            if (!usedImageUrls.contains(file.getName())) {
                file.delete();
            }
        }
    }

    private List<String> getAllUsedImageUrls() {
        List<UserEntity> users = userRepository.findAll();
        List<String> userUrls = new ArrayList<String>();
        for(UserEntity user : users){
            userUrls.add(user.getUrlProfilePicture());
        }
        return userUrls;
    }
}
