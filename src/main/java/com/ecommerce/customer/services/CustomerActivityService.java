package com.ecommerce.customer.services;

import com.ecommerce.customer.dto.RegisterCustomerRequest;
import com.ecommerce.customer.dto.ChangePasswordRequest;
import com.ecommerce.customer.dto.ValidateCustomerRequest;
import com.ecommerce.customer.repo.custAccount.CustomerAccount;
import com.ecommerce.customer.repo.custAccount.CustomerAccountDao;
import com.ecommerce.customer.repo.custDetail.CustomerDetail;
import com.ecommerce.customer.repo.custDetail.CustomerDetailDao;
import com.ecommerce.customer.repo.loginHistory.LoginHistory;
import com.ecommerce.customer.repo.loginHistory.LoginHistoryDao;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.NoSuchElementException;

@Service
public class CustomerActivityService implements CustomerActivity {

    private final CustomerAccountDao customerAccountDao;
    private final CustomerDetailDao customerDetailDao;
    private final LoginHistoryDao loginHistoryDao;


    public CustomerActivityService(CustomerAccountDao customerAccountDao, CustomerDetailDao customerDetailDao, LoginHistoryDao loginHistoryDao) {
        this.customerAccountDao = customerAccountDao;
        this.customerDetailDao = customerDetailDao;
        this.loginHistoryDao = loginHistoryDao;
    }


    @Override
    public void registerCustomer(RegisterCustomerRequest registerCustomer) {
        customerDetailDao.findByIdentityNymber(registerCustomer.getIdentityNumber()).ifPresent(x ->{ throw new DataIntegrityViolationException("Duplicate data found");});

        CustomerDetail customerDetail = CustomerDetail.builder()
                .email(registerCustomer.getMail())
                .nationalIdentityNumber(registerCustomer.getIdentityNumber())
                .name(registerCustomer.getName())
                .birthDate(registerCustomer.getBirthDate())
                .build();
        Long userId = customerDetailDao.save(customerDetail);


        customerAccountDao.findByUsername(registerCustomer.getUsername()).ifPresent(x -> {throw new DataIntegrityViolationException("Duplicate data found");});
        CustomerAccount customerAccount = CustomerAccount.builder()
                .id(userId)
                .password(registerCustomer.getPassword())
                .username(registerCustomer.getUsername())
                .build();
        customerAccountDao.save(customerAccount);
    }

    @Override
    public void validateCustomer(ValidateCustomerRequest validateCustomer) {
        customerAccountDao.findByUsernameAndPassword(validateCustomer.getUsername(), validateCustomer.getPassword()).ifPresentOrElse(
                value -> this.loginHistoryDao.save(new LoginHistory(value.getId(), new Date(), "SUCCESS")),
                () -> {
                    throw new NoSuchElementException("invalid username or password");
        }
        );
    }

    @Override
    public void changePassword(ChangePasswordRequest resetPassword) {
        CustomerAccount customerAccount = customerAccountDao.findByUsername(resetPassword.getUsername()).orElseThrow(() -> new NoSuchElementException("data Not Found"));
        customerAccount.setPassword(resetPassword.getNewPassword());
        customerAccountDao.updatePassword(customerAccount.getId(), resetPassword.getNewPassword());
    }
}
