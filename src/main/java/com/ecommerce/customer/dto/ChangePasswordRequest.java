package com.ecommerce.customer.dto;

import lombok.Data;

@Data
public class ChangePasswordRequest {
    private String username;
    private String newPassword;
}