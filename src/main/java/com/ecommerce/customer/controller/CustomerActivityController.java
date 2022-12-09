package com.ecommerce.customer.controller;

import com.ecommerce.customer.dto.*;
import com.ecommerce.customer.services.CustomerActivity;
import com.ecommerce.customer.services.CustomerManaging;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@Slf4j
@RestController
@RequestMapping("customer")
public class CustomerActivityController {
    private final CustomerActivity customerActivityService;
    private final CustomerManaging customerManaging;

    public CustomerActivityController(CustomerActivity customerActivityService, CustomerManaging customerManaging) {
        this.customerActivityService = customerActivityService;
        this.customerManaging = customerManaging;
    }

    @PostMapping("register")
    public void register(HttpServletRequest httpRequest
            , @RequestBody @Valid RegisterCustomerRequest registerCustomer) {
        this.customerActivityService.register(registerCustomer);
    }

    @PostMapping("login")
    public LoginResponse login(HttpServletRequest httpRequest, @Validated @RequestBody LoginRequest loginRequest) {
        return this.customerActivityService.login(loginRequest);
    }

    @DeleteMapping("logout")
    public void logout(HttpServletRequest httpRequest
            , @RequestHeader("user-id") String userid
            , @RequestHeader("session-id") String sessionId) {
        this.customerActivityService.validateSession(new ValidateSessionRequest(sessionId, Long.parseLong(userid)));
        this.customerActivityService.logout(new LogoutRequest(Long.parseLong(userid)));
    }

    @GetMapping("validate-session")
    public void validateSession(HttpServletRequest httpRequest
            , @RequestHeader("user-id") String userid
            , @RequestHeader("session-id") String sessionId) {
        this.customerActivityService.validateSession(new ValidateSessionRequest(sessionId, Long.parseLong(userid)));
    }

    @GetMapping
    public DetailCustomerResponse detail(HttpServletRequest httpRequest
            , @RequestHeader("user-id") String userid
            , @RequestHeader("session-id") String sessionId) {
        this.customerActivityService.validateSession(new ValidateSessionRequest(sessionId, Long.parseLong(userid)));
        return this.customerManaging.detailCustomer(userid);
    }

    @PostMapping("validate-exist-user")
    public ValidateExistingCustomerResponse validateExistUser(HttpServletRequest httpRequest
            , @RequestBody @Valid ValidateExistingCustomerRequest validateCustomer) {
        return this.customerManaging.requestChangePassword(validateCustomer);
    }

    @PatchMapping("change-password")
    public void changePassword(HttpServletRequest httpRequest
            , @RequestBody @Valid ChangePasswordRequest changePassword) {
        this.customerManaging.changePassword(changePassword);
    }

}
