package com.customer.customer.dto;

import lombok.Data;

@Data
public class ValidateCustomerRequest {
    private String username;
    private String password;
}
