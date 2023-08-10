package com.stefanogiuseppe.carsharing.controller;

import com.stefanogiuseppe.carsharing.dto.UserDTO;
import com.stefanogiuseppe.carsharing.entity.UserEntity;
import com.stefanogiuseppe.carsharing.mapper.UserMapper;
import com.stefanogiuseppe.carsharing.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(path = "/api/user")
@CrossOrigin(origins = "*")
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private UserMapper userMapper;
    /*private ModelMapper modelMapper;

    @Autowired
    public UserController(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }*/

    @PostMapping("")
    @ResponseBody
    public UserDTO addUser(@RequestBody UserDTO userDTO) {
        UserEntity userEntity = userMapper.toEntity(userDTO);
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
        List<UserDTO> userDTO = new ArrayList<>();
        for (UserEntity u : userEntities) {
            UserDTO userDTO1 = userMapper.toDTO(u);
            userDTO.add(userDTO1);
        }
        return userDTO;
    }
  /*  @GetMapping("")
    @ResponseBody
    public List<UserDTO> getAllUser() {
        List<UserEntity> userEntities = userService.getAllUser();
        List<UserDTO> userDTO = userEntities.stream()
                .map(user -> modelMapper.map(user, UserDTO.class))
                .collect(Collectors.toList());
        return userDTO;
    }*/

    @GetMapping("/{id}")
    @ResponseBody
    public UserDTO getUserById(@PathVariable Long id) {
        UserEntity userEntity = userService.findById(id);
        UserDTO userDTO = userMapper.toDTO(userEntity);;
        return userDTO;
    }

    @PatchMapping("/{id}")
    @ResponseBody
    public UserDTO updatePatchUser(@PathVariable Long id, @RequestBody UserDTO userDTO) {

        UserEntity userEntity = userService.updatePatchUser(id, userDTO);

        UserDTO userDTO1 = userMapper.toDTO(userEntity);

        return userDTO1;
    }

    @PutMapping("/{id}")
    @ResponseBody
    public UserDTO updatePutUser(@PathVariable Long id, @RequestBody UserDTO userDTO) {

        UserEntity userEntity = userService.updatePutUser(id, userDTO);

        UserDTO userDTO1 = userMapper.toDTO(userEntity);

        return userDTO1;
    }

    @DeleteMapping("/{id}")
    @ResponseBody
    public void deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        System.out.println("User " + id + " deleted");
    }
}
