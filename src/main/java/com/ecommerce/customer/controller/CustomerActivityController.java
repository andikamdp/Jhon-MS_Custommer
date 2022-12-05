package com.ecommerce.customer.controller;

import com.ecommerce.customer.dto.ChangePasswordRequest;
import com.ecommerce.customer.dto.RegisterCustomerRequest;
import com.ecommerce.customer.dto.ValidateCustomerRequest;
import com.ecommerce.customer.services.CustomerActivityService;
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
