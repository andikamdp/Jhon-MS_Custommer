package com.ecommerce.customer.repo.changePassword;

import com.ecommerce.customer.utils.CommonStatus;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.Optional;

@Component
public class ChagePasswordDao {
    private final ChangePasswordRepository changePasswordRepository;


    public ChagePasswordDao(ChangePasswordRepository changePasswordRepository) {
        this.changePasswordRepository = changePasswordRepository;
    }

    public String save(Long userId) {
        ChangePasswordModel init = new ChangePasswordModel(userId, new Date(), CommonStatus.INIT.name());
        init = this.changePasswordRepository.save(init);
        return init.getTransactionId();
    }

    public void updateStatus(String transactionId, Long userId, CommonStatus commonStatus) {
        ChangePasswordModel usedTransactionId = new ChangePasswordModel(transactionId, userId, new Date(), commonStatus.name());
        this.changePasswordRepository.save(usedTransactionId);
    }

    public Optional<ChangePasswordModel> findById(Long userId) {
        return this.changePasswordRepository.findByUserIdAndStatus(userId, CommonStatus.INIT.name());
    }

    public Optional<ChangePasswordModel> findById(String transactionId, Long userId) {
        return this.changePasswordRepository.findByTransactionIdAndUserIdAndStatus(transactionId, userId, CommonStatus.INIT.name());
    }


}
