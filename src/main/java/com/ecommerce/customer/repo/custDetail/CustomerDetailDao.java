package com.ecommerce.customer.repo.custDetail;

import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class CustomerDetailDao {

    private final CustomerDetailRepository customerDetailRepository;

    public CustomerDetailDao(CustomerDetailRepository customerDetailRepository) {
        this.customerDetailRepository = customerDetailRepository;
    }

    public void save(CustomerDetail data) {
        customerDetailRepository.save(data);
    }

    public Optional<CustomerDetail> findByIdentityNumberAndUsername(String identityNumber, String username) {
        return this.customerDetailRepository.findByNationalIdentityNumberAndAccountUsername(identityNumber, username);
    }

    public Optional<CustomerDetail> findByIdentityNumberOrUsername(String identityNumber, String username) {
        return this.customerDetailRepository.findByNationalIdentityNumberOrAccountUsername(identityNumber, username);
    }
}
