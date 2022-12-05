package com.ecommerce.customer.repo.custDetail;

import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class CustomerDetailDao {

    private final CustomerDetailRepository customerDetailRepository;

    public CustomerDetailDao(CustomerDetailRepository customerDetailRepository) {
        this.customerDetailRepository = customerDetailRepository;
    }

    public Long save(CustomerDetail data) {
        return customerDetailRepository.save(data).getUserId();
    }

    public Optional<CustomerDetail> findById(Long id) {
        return this.customerDetailRepository.findById(id);
    }

    public Optional<CustomerDetail> findByIdentityNymber(String identityNumber) {
        return this.customerDetailRepository.findByNationalIdentityNumber(identityNumber);
    }
}
