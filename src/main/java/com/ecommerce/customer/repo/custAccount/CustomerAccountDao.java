package com.ecommerce.customer.repo.custAccount;

import org.springframework.context.annotation.Configuration;

import java.util.Optional;

@Configuration
public class CustomerAccountDao {
    private final CustomerAccountRepository customerAccountRepository;

    public CustomerAccountDao(CustomerAccountRepository customerAccountRepository) {
        this.customerAccountRepository = customerAccountRepository;
    }

    public CustomerAccountModel save(CustomerAccountModel data) {
        return customerAccountRepository.save(data);
    }

    public Optional<CustomerAccountModel> findById(Long id) {
        return this.customerAccountRepository.findById(id);
    }

    public Optional<CustomerAccountModel> findByUsername(String username) {
        return this.customerAccountRepository.findByUsername(username);
    }

    public Optional<CustomerAccountModel> findByUsernameAndPassword(String username, String password) {
        return this.customerAccountRepository.findByUsernameAndPassword(username, password);
    }

    public void updatePassword(Long userId, String password) {
        CustomerAccountModel customerAccount = this.customerAccountRepository.getReferenceById(userId);
        customerAccount.setPassword(password);

        this.save(customerAccount);

    }
}
