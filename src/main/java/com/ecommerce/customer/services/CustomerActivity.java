package com.ecommerce.customer.services;

import com.ecommerce.customer.dto.*;

public interface CustomerActivity {
    void register(RegisterCustomerRequest registerCustomer);
    LoginResponse login(LoginRequest loginRequest);
    void logout(LogoutRequest logoutRequest);
    void validateSession(ValidateSessionRequest validateSessionRequest);

}
