package com.ecommerce.customer.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ValidateExistingCustomerResponse {
    private String transactionId;
    private Long userId;
}
