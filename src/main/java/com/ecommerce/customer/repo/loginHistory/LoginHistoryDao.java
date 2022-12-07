package com.ecommerce.customer.repo.loginHistory;

import org.springframework.stereotype.Component;

@Component
public class LoginHistoryDao {
    private final LoginHistoryRepository loginHistoryRepository;

    public LoginHistoryDao(LoginHistoryRepository loginHistoryRepository) {
        this.loginHistoryRepository = loginHistoryRepository;
    }

    public void save(LoginHistoryModel data) {
        loginHistoryRepository.save(data);
    }
}
