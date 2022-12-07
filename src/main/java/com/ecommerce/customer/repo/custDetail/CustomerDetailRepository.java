package com.ecommerce.customer.repo.custDetail;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CustomerDetailRepository extends JpaRepository<CustomerDetailModel, String> {
    Optional<CustomerDetailModel> findByNationalIdentityNumberAndAccountUsername(String identityNumber, String username);

    Optional<CustomerDetailModel> findByNationalIdentityNumberOrAccountUsername(String identityNumber, String username);

    Optional<CustomerDetailModel> findByAccount_UserId(Long userId);
}
