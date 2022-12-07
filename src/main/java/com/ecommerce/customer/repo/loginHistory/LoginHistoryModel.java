package com.ecommerce.customer.repo.loginHistory;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class LoginHistoryModel {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;
    private Long customerAccountId;
    private Date loginDate;
    private String loginStatus;

    public LoginHistoryModel(Long customerAccountId, Date loginDate, String loginStatus) {
        this.customerAccountId = customerAccountId;
        this.loginDate = loginDate;
        this.loginStatus = loginStatus;
    }
}
