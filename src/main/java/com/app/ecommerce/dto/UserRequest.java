package com.app.ecommerce.dto;


import com.app.ecommerce.model.UserRole;
import lombok.Data;

@Data
public class UserRequest {

    private String firstName;
    private String surname;
    private String email;
    private String phone;
    private AddressDto address;
}
