package com.customer.customer.repo.custAccount;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CustomerAccountRepository extends JpaRepository<CustomerAccount, Long> {
    Optional<CustomerAccount> findByUsername(String username);

    Optional<CustomerAccount> findByUsernameAndPassword(String username, String password);
}
