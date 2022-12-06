package com.ecommerce.customer.repo.custDetail;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CustomerDetailRepository extends JpaRepository<CustomerDetail, String> {
    Optional<CustomerDetail> findByNationalIdentityNumberAndAccountUsername(String identityNumber, String username);

    Optional<CustomerDetail> findByNationalIdentityNumberOrAccountUsername(String identityNumber, String username);
    Optional<CustomerDetail> findByAccount_UserId(Long userId);
}
