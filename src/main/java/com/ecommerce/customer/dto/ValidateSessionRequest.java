package com.ecommerce.customer.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ValidateSessionRequest {
    private String Session;
    private Long userId;
}
