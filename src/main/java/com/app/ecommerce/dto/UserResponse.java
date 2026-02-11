package com.app.ecommerce.dto;

import com.app.ecommerce.model.UserRole;
import lombok.Data;

import java.util.UUID;

@Data
public class UserResponse {
    private String id;
    private String firstName;
    private String surname;
    private String email;
    private String phone;
    private UserRole role;
    private AddressDto address;
}
