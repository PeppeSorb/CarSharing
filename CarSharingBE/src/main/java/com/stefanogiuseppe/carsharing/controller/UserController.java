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
        List<RechargeEntity> rechargeEntities = rechargeService.getAllRecharge();
        List<RechargeDTO> rechargeDTO = rechargeEntities.stream()
                .map(recharge -> modelMapper.map(recharge, RechargeDTO.class))
                .collect(Collectors.toList());
        return ResponseEntity.ok(rechargeDTO);
    }

    @GetMapping("/{id}")
    public ResponseEntity<RechargeDTO> getRechargeById(@PathVariable Long id) {
        RechargeEntity rechargeEntity = rechargeService.findById(id);
        RechargeDTO rechargeDTO = modelMapper.map(rechargeEntity, RechargeDTO.class);
        return ResponseEntity.ok(rechargeDTO);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<RechargeDTO> updateRecharge(@PathVariable Long id, @RequestBody RechargeDTO rechargeDTO) {

        RechargeEntity rechargeEntity = rechargeService.updateRecharge(id, rechargeDTO);

        RechargeDTO rechargeDTO1 = modelMapper.map(rechargeEntity, RechargeDTO.class);

        return ResponseEntity.ok(rechargeDTO1);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteRecharge(@PathVariable Long id) {
        rechargeService.deleteRecharge(id);
        return ResponseEntity.ok("Recharge " + id + " deleted");
    }
}
