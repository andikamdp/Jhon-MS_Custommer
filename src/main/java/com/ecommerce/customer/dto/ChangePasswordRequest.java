package com.ecommerce.customer.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class ChangePasswordRequest {
    @NotNull(message = "{error.empty-field}")
    private Long userId;
    @NotBlank(message = "{error.empty-field}")
    private String transactionId;
    @NotBlank(message = "{error.empty-field}")
    private String newPassword;
}
