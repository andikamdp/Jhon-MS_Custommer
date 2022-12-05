package com.customer.customer.repo.custDetail;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CustomerDetailRepository extends JpaRepository<CustomerDetail, Long> {
    Optional<CustomerDetail> findByNationalIdentityNumber(String identityNumber);
}
