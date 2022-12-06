package com.ecommerce.customer.services;

import com.ecommerce.customer.dto.*;

public interface CustomerActivity {
    void registerCustomer(RegisterCustomerRequest registerCustomer);

    void validateCustomer(ValidateCustomerRequest validateCustomer);

    ValidateExistingCustomerResponse requestChangePassword(ValidateExistingCustomerRequest resetPassword);

    void changePassword(ChangePasswordRequest resetPassword);
}
