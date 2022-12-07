package com.ecommerce.customer.repo.custDetail;

import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class CustomerDetailDao {

    private final CustomerDetailRepository customerDetailRepository;

    public CustomerDetailDao(CustomerDetailRepository customerDetailRepository) {
        this.customerDetailRepository = customerDetailRepository;
    }

    public void save(CustomerDetailModel data) {
        customerDetailRepository.save(data);
    }

    public Optional<CustomerDetailModel> findByIdentityNumberAndUsername(String identityNumber, String username) {
        return this.customerDetailRepository.findByNationalIdentityNumberAndAccountUsername(identityNumber, username);
    }

    public Optional<CustomerDetailModel> findByIdentityNumberOrUsername(String identityNumber, String username) {
        return this.customerDetailRepository.findByNationalIdentityNumberOrAccountUsername(identityNumber, username);
    }

    public Optional<CustomerDetailModel> findByAccount_UserId(Long userId) {
        return this.customerDetailRepository.findByAccount_UserId(userId);
    }
}
