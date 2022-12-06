package com.ecommerce.customer.repo.session;

import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.Optional;

@Component
public class SessionDao {
    private final SessionRepository sessionRepository;

    public SessionDao(SessionRepository sessionRepository) {
        this.sessionRepository = sessionRepository;
    }

    public String save(Long userId){
        Session session = new Session();
        session.setDate(new Date());
        session.setUserId(userId);
        session = this.sessionRepository.save(session);

        return session.getTransactionId();
    }

    public String updateDate(Long userId, String sessionId){
        Session session = new Session();
        session.setDate(new Date());
        session.setUserId(userId);
        session.setTransactionId(sessionId);
        session = this.sessionRepository.save(session);

        return session.getTransactionId();
    }

    public void delete(Long userId){
        this.sessionRepository.deleteByUserId(userId);
    }

    public Optional<Session> findById(String sessionId){
        return this.sessionRepository.findById(sessionId);
    }
}
