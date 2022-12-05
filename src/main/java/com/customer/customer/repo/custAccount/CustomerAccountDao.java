package com.customer.customer.repo.custAccount;

import org.springframework.context.annotation.Configuration;

import java.util.NoSuchElementException;
import java.util.Optional;

@Configuration
public class CustomerAccountDao {
    private final CustomerAccountRepository customerAccountRepository;

    public CustomerAccountDao(CustomerAccountRepository customerAccountRepository) {
        this.customerAccountRepository = customerAccountRepository;
    }

    public void save(CustomerAccount data) {
        customerAccountRepository.save(data);
    }

    public CustomerAccount findById(Long id) {
        return this.customerAccountRepository.findById(id).orElseThrow();
    }

    public Optional<CustomerAccount> findByUsername(String username) {
        return this.customerAccountRepository.findByUsername(username);
    }

    public Optional<CustomerAccount> findByUsernameAndPassword(String username, String password) {
        return this.customerAccountRepository.findByUsernameAndPassword(username, password);
    }

    public void updatePassword(Long userId, String password) {
        CustomerAccount customerAccount = this.customerAccountRepository.getReferenceById(userId);
        customerAccount.setPassword(password);

        this.save(customerAccount);

    }
}
