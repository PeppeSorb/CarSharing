package com.stefanogiuseppe.carsharing.config;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Data
public class LoginRequest {
    private String email;
    private String password;
}