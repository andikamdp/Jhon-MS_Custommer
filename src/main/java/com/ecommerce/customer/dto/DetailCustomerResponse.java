package com.ecommerce.customer.dto;

import lombok.Data;

import java.util.Date;

@Data
public class DetailCustomerResponse {
    private String nationalIdentityNumber;
    private String name;
    private String email;
    private Date birthDate;
    private Long userId;
    private String username;
}
