package com.ecommerce.customer.repo.changePassword;

import com.ecommerce.customer.utils.CommonStatus;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.Optional;
import java.util.UUID;

@Component
public class ChagePasswordDao {
    private final ChangePasswordRepository changePasswordRepository;


    public ChagePasswordDao(ChangePasswordRepository changePasswordRepository) {
        this.changePasswordRepository = changePasswordRepository;
    }

    public String save(Long userId) {
        ChangePassword init = new ChangePassword(userId, new Date(), CommonStatus.INIT.name());
        init = this.changePasswordRepository.save(init);
        return init.getTransactionId();
    }

    public void updateStatus(String transactionId, Long userId, CommonStatus commonStatus){
        ChangePassword usedTransactionId = new ChangePassword(transactionId, userId, new Date(), commonStatus.name());
        this.changePasswordRepository.save(usedTransactionId);
    }

    public Optional<ChangePassword> findById(String transactionId, Long userId){
      return this.changePasswordRepository.findByTransactionIdAndUserIdAndStatus(transactionId, userId, CommonStatus.INIT.name());
    }
}
