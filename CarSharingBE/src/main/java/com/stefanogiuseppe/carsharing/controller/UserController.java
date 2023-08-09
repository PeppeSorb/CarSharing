package com.stefanogiuseppe.carsharing.controller;

import com.stefanogiuseppe.carsharing.dto.RechargeDTO;
import com.stefanogiuseppe.carsharing.dto.UserDTO;
import com.stefanogiuseppe.carsharing.entity.RechargeEntity;
import com.stefanogiuseppe.carsharing.entity.UserEntity;
import com.stefanogiuseppe.carsharing.service.RechargeService;
import com.stefanogiuseppe.carsharing.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(path = "/api/user")
@CrossOrigin(origins = "*")
public class UserController {
    @Autowired
    private UserService userService;
    private ModelMapper modelMapper;

    @Autowired
    public UserController(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @PostMapping("")
    public ResponseEntity<UserDTO> addUser(@RequestBody UserDTO userDTO) {
        UserEntity userEntity = modelMapper.map(userDTO, UserEntity.class);
        UserEntity userEntity1 = userService.saveUser(userEntity);
        UserDTO userDTO1 = modelMapper.map(userEntity1, UserDTO.class);
        return ResponseEntity.status(HttpStatus.CREATED).body(userDTO1);
    }

    @GetMapping("")
    public ResponseEntity<List<UserDTO>> getAllUser() {
        List<UserEntity> userEntities = userService.getAllUser();
        List<UserDTO> userDTO = userEntities.stream()
                .map(user -> modelMapper.map(user, UserDTO.class))
                .collect(Collectors.toList());
        return ResponseEntity.ok(userDTO);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDTO> getUserById(@PathVariable Long id) {
        UserEntity userEntity = userService.findById(id);
        UserDTO userDTO = modelMapper.map(userEntity, UserDTO.class);
        return ResponseEntity.ok(userDTO);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<UserDTO> updateUser(@PathVariable Long id, @RequestBody UserDTO userDTO) {

        UserEntity userEntity = userService.updateUser(id, userDTO);

        UserDTO userDTO1 = modelMapper.map(userEntity, UserDTO.class);

        return ResponseEntity.ok(userDTO1);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return ResponseEntity.ok("User " + id + " deleted");
    }
}
