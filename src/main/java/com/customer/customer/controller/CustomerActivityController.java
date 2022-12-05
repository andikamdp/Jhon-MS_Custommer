package com.customer.customer.controller;

import com.customer.customer.dto.ChangePasswordRequest;
import com.customer.customer.dto.RegisterCustomerRequest;
import com.customer.customer.dto.ValidateCustomerRequest;
import com.customer.customer.services.CustomerActivityService;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("customer")
public class CustomerActivityController {
    private final CustomerActivityService customerActivityService;

    public CustomerActivityController(CustomerActivityService customerActivityService) {
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

    @PatchMapping("change-password")
    public void changePassword(HttpServletRequest httpRequest, @RequestBody ChangePasswordRequest changePassword){
        customerActivityService.changePassword(changePassword);
    }

}
