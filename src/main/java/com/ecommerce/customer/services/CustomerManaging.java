package com.ecommerce.customer.services;

import com.ecommerce.customer.dto.ChangePasswordRequest;
import com.ecommerce.customer.dto.DetailCustomerResponse;
import com.ecommerce.customer.dto.ValidateExistingCustomerRequest;
import com.ecommerce.customer.dto.ValidateExistingCustomerResponse;

public interface CustomerManaging {
    ValidateExistingCustomerResponse requestChangePassword(ValidateExistingCustomerRequest resetPassword);
    void changePassword(ChangePasswordRequest resetPassword);
    DetailCustomerResponse detailCustomer(String userId);

}
