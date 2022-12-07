package com.ecommerce.customer.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class ValidateExistingCustomerRequest {
    @NotBlank(message = "{error.empty-field}")
    private String nationalIdentityNumber;
    @NotBlank(message = "{error.empty-field}")
    private String username;
}
