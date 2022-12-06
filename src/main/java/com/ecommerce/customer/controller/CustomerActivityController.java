package com.ecommerce.customer.controller;

import com.ecommerce.customer.dto.*;
import com.ecommerce.customer.services.CustomerActivity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("customer")
public class CustomerActivityController {
    private final CustomerActivity customerActivityService;

    public CustomerActivityController(CustomerActivity customerActivityService) {
        this.customerActivityService = customerActivityService;
    }


    @PostMapping("register")
    public void register(HttpServletRequest httpRequest, @RequestBody RegisterCustomerRequest registerCustomer){
        customerActivityService.registerCustomer(registerCustomer);
    }

    @PostMapping("validate")
    public void validate(HttpServletRequest httpRequest, @RequestBody ValidateCustomerRequest validateCustomer){
        customerActivityService.validateCustomer(validateCustomer);
    }

    @PostMapping("validate-exist-user")
    public ValidateExistingCustomerResponse validateExistUser(HttpServletRequest httpRequest, @RequestBody ValidateExistingCustomerRequest validateCustomer){
       return customerActivityService.requestChangePassword(validateCustomer);
    }

    @PatchMapping("change-password")
    public void changePassword(HttpServletRequest httpRequest, @RequestBody ChangePasswordRequest changePassword){
        customerActivityService.changePassword(changePassword);
    }

}
