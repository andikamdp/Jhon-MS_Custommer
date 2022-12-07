package com.ecommerce.customer.repo.changePassword;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ChangePasswordRepository extends JpaRepository<ChangePasswordModel, String> {

    Optional<ChangePasswordModel> findByTransactionIdAndUserIdAndStatus(String transactionId, Long userId, String status);
}
