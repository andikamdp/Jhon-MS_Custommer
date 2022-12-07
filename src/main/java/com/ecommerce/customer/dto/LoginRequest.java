package com.ecommerce.customer.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class LoginRequest {
    @NotBlank(message = "{error.empty-field}")
    private String username;
    @NotBlank(message = "{error.empty-field}")
    private String password;
}
