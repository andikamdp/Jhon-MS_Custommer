package com.ecommerce.customer.repo.session;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
public interface SessionRepository extends JpaRepository<Session, String> {
    @Modifying
    @Transactional
    void deleteByUserId(Long userId);
}
