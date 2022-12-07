package com.ecommerce.customer.repo.custAccount;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CustomerAccountRepository extends JpaRepository<CustomerAccountModel, Long> {
    Optional<CustomerAccountModel> findByUsername(String username);

    Optional<CustomerAccountModel> findByUsernameAndPassword(String username, String password);
}
