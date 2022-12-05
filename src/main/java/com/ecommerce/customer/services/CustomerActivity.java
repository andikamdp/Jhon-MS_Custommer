package com.ecommerce.customer.services;

import com.ecommerce.customer.dto.RegisterCustomerRequest;
import com.ecommerce.customer.dto.ChangePasswordRequest;
import com.ecommerce.customer.dto.ValidateCustomerRequest;

public interface CustomerActivity {
    void registerCustomer(RegisterCustomerRequest registerCustomer);

    void validateCustomer(ValidateCustomerRequest validateCustomer);

    void changePassword(ChangePasswordRequest resetPassword);
}
