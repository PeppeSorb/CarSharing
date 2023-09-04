package com.stefanogiuseppe.carsharing.controller;

import com.stefanogiuseppe.carsharing.dto.UserDTO;
import com.stefanogiuseppe.carsharing.entity.UserEntity;
import com.stefanogiuseppe.carsharing.mapper.UserMapper;
//import com.stefanogiuseppe.carsharing.service.AuthenticationService;
import com.stefanogiuseppe.carsharing.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

@RestController
@Tag(name = "Users Controller", description = "This controller allows create, read, update and delete operations on Users")
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

    //@Autowired
    //private AuthenticationService authenticateService;

    @PostMapping("/login")
    public String login(@RequestBody String email, String password) {
        //String token = authenticateService.authenticateUser(loginRequest.getEmail(), loginRequest.getPassword());
        //System.out.println(token);
        // Restituisci il token come risposta
        return email;//token;
    }

    @PostMapping("")
    @ResponseBody
    @Operation(description = "Adds a new user to the repository")
    public UserDTO addUser(@Parameter(description = "The new user in a JSON format") @RequestBody UserDTO userDTO) {
        UserEntity userEntity = userMapper.toEntity(userDTO);
        //UserEntity userEntity = modelMapper.map(userDTO, UserEntity.class);
        UserEntity userEntity1 = userService.saveUser(userEntity);
        UserDTO userDTO1 = userMapper.toDTO(userEntity1);
        //UserDTO userDTO1 = modelMapper.map(userEntity1, UserDTO.class);
        return userDTO1;
    }

    @GetMapping("")
    @ResponseBody
    @Operation(description = "Returns all users of the repository")
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
    @Operation(description = "Returns a user inside the repository with the specified id")
    public UserDTO getUserById(@Parameter(description = "The id of the requested user") @PathVariable Long id) {
        UserEntity userEntity = userService.findById(id);
        UserDTO userDTO = userMapper.toDTO(userEntity);
        ;
        return userDTO;
    }

    @PatchMapping("/{id}")
    @ResponseBody
    @Operation(description = "Updates some information of an existing user")
    public UserDTO updatePatchUser(@Parameter(description = "The id of the user to update") @PathVariable Long id, @Parameter(description = "The updated user") @RequestBody UserDTO userDTO) {

        UserEntity userEntity = userService.updateUser(id, userDTO);

        UserDTO userDTO1 = userMapper.toDTO(userEntity);

        return userDTO1;
    }

    @PutMapping("/{id}")
    @ResponseBody
    @Operation(description = "Updates all information of an existing user")
    public UserDTO updatePutUser(@Parameter(description = "The id of the user to update") @PathVariable Long id, @Parameter(description = "The updated information of user") @RequestBody UserDTO userDTO) {

        UserEntity userEntity = userService.updateUser(id, userDTO);

        UserDTO userDTO1 = userMapper.toDTO(userEntity);

        return userDTO1;
    }

    @DeleteMapping("/{id}")
    @ResponseBody
    @Operation(description = "Deletes a user from the repository")
    public void deleteUser(@Parameter(description = "The id of the user to delete") @PathVariable Long id) {
        userService.deleteUser(id);
        System.out.println("User " + id + " deleted");
    }

    @GetMapping("/verify/{userId}")
    @Operation(description = "When this method is called, the user's email gets verified.")
    public String verifyEmail(@Parameter(description = "The id of the user to verify") @PathVariable Long userId) {
        UserEntity user = userService.findById(userId);
        if (user.isEmailIsVerified() == false) {
            user.setEmailIsVerified(true);
            userService.saveUser(user);
            return ("Account verificato con successo!");
        } else {
            return ("L'account è già stato verificato.");
        }
    }
    @PostMapping(value = "/upload-image/{userId}", consumes = {"multipart/form-data"})
    @Operation(description = "Uploads an image to the server for a user")
    public String uploadImageToUser(@Parameter(description = "The id of the user to update") @PathVariable Long userId, @Parameter(description = "The image file to upload") @RequestParam("file") MultipartFile file) {
        return userService.uploadImageToUser(userId,file);
    }
}
