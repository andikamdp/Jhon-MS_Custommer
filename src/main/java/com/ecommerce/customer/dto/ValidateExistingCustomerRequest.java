package com.ecommerce.customer.dto;

import lombok.Data;

@Data
public class ValidateExistingCustomerRequest {
    private String nationalIdentityNumber;
    private String username;
}
