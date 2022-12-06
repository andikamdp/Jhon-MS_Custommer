package com.ecommerce.customer.dto;

import lombok.Data;

@Data
public class ChangePasswordRequest {
    private Long userId;
    private String transactionId;
    private String newPassword;
}
