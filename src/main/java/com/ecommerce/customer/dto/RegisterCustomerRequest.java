package com.ecommerce.customer.dto;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Data
public class RegisterCustomerRequest {
    @NotBlank(message = "{error.empty-field}")
    private String identityNumber;
    @NotBlank(message = "{error.empty-field}")
    private String name;
    @Email(message = "error.mail-format")
    private String mail;
    @NotNull(message = "{error.empty-field}")
    private Date birthDate;
    @NotBlank(message = "{error.empty-field}")
    private String username;
    @NotBlank(message = "{error.empty-field}")
    private String password;
}
