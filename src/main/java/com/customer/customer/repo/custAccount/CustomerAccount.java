package com.customer.customer.repo.custAccount;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CustomerAccount {
    @Id
    private Long id;

    @Column(unique = true)
    private String username;
    private String password;
}
