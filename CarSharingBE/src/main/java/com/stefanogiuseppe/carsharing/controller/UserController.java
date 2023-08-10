package com.stefanogiuseppe.carsharing.controller;

import com.stefanogiuseppe.carsharing.dto.UserDTO;
import com.stefanogiuseppe.carsharing.entity.UserEntity;
import com.stefanogiuseppe.carsharing.mapper.UserMapper;
import com.stefanogiuseppe.carsharing.service.UserService;
import org.apache.catalina.User;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(path = "/api/user")
@CrossOrigin(origins = "*")
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private UserMapper userMapper;
    private ModelMapper modelMapper;

    @Autowired
    public UserController(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @PostMapping("")
    @ResponseBody
    public UserDTO addUser(@RequestBody UserDTO userDTO) {
        UserEntity userEntity=userMapper.toEntity(userDTO);
        //UserEntity userEntity = modelMapper.map(userDTO, UserEntity.class);
        UserEntity userEntity1 = userService.saveUser(userEntity);
        UserDTO userDTO1 = userMapper.toDTO(userEntity1);
        //UserDTO userDTO1 = modelMapper.map(userEntity1, UserDTO.class);
        return userDTO1;
    }

    @GetMapping("")
    @ResponseBody
    public List<UserDTO> getAllUser() {
        List<UserEntity> userEntities = userService.getAllUser();
        List<UserDTO> userDTO = userEntities.stream()
                .map(user -> modelMapper.map(user, UserDTO.class))
                .collect(Collectors.toList());
        return userDTO;
    }

    @GetMapping("/{id}")
    @ResponseBody
    public UserDTO getUserById(@PathVariable Long id) {
        UserEntity userEntity = userService.findById(id);
        UserDTO userDTO = modelMapper.map(userEntity, UserDTO.class);
        return userDTO;
    }

    @PatchMapping("/{id}")
    @ResponseBody
    public UserDTO updateUser(@PathVariable Long id, @RequestBody UserDTO userDTO) {

        UserEntity userEntity = userService.updateUser(id, userDTO);

        UserDTO userDTO1 = modelMapper.map(userEntity, UserDTO.class);

        return userDTO1;
    }

    @DeleteMapping("/{id}")
    @ResponseBody
    public void deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        System.out.println("User " + id + " deleted");
    }
}
