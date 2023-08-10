package com.stefanogiuseppe.carsharing.dto;

import com.stefanogiuseppe.carsharing.entity.RechargeEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class UserDTO {
    private Long id;
    private String pivaOrCf;
    private String idDrivingLicense;
    private String email;
    private String firstName;
    private String surname;
    private String password;
    private boolean emailIsVerified;
    private String urlProfilePicture;
    //private List<RechargeEntity> recharges;


}
