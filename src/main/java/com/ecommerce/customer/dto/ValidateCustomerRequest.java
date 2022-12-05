package com.ecommerce.customer.dto;

import lombok.Data;

@Data
public class ValidateCustomerRequest {
    private String username;
    private String password;
}
