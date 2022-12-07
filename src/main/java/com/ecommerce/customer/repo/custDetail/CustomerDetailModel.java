package com.ecommerce.customer.repo.custDetail;

import com.ecommerce.customer.repo.custAccount.CustomerAccountModel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CustomerDetailModel {
    @Id
    private String nationalIdentityNumber;
    private String name;
    private String email;
    private Date birthDate;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "customer_account", referencedColumnName = "user_id")
    private CustomerAccountModel account;
}
