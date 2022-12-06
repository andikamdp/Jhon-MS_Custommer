package com.ecommerce.customer.repo.custDetail;

import com.ecommerce.customer.repo.custAccount.CustomerAccount;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Email;
import java.util.Date;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CustomerDetail {
    @Id
    private String nationalIdentityNumber;
    private String name;
    @Email
    private String email;
    private Date birthDate;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "customer_account", referencedColumnName = "user_id")
    private CustomerAccount account;
}
