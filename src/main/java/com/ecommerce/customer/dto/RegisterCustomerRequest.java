package com.ecommerce.customer.dto;

import lombok.Data;

import java.util.Date;

@Data
public class RegisterCustomerRequest {
    private String identityNumber;
    private String name;
    private String mail;
    private Date birthDate;
    private String username;
    private String password;
}
