package com.customer.customer.services;

import com.customer.customer.dto.RegisterCustomerRequest;
import com.customer.customer.dto.ChangePasswordRequest;
import com.customer.customer.dto.ValidateCustomerRequest;

public interface CustomerActivity {
    void registerCustomer(RegisterCustomerRequest registerCustomer);

    void validateCustomer(ValidateCustomerRequest validateCustomer);

    void changePassword(ChangePasswordRequest resetPassword);
}
