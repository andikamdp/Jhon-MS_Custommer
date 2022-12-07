package com.ecommerce.customer.utils;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties("param")
@Data
public class ParameterComponent {
    private Integer sessionTimeout;
}
