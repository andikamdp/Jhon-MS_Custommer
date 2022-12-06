package com.ecommerce.customer.services;

import com.ecommerce.customer.dto.*;
import com.ecommerce.customer.repo.changePassword.ChagePasswordDao;
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
    private final ChagePasswordDao chagePasswordDao;
    private final LoginHistoryDao loginHistoryDao;


    public CustomerActivityService(CustomerAccountDao customerAccountDao, CustomerDetailDao customerDetailDao, ChagePasswordDao chagePasswordDao, LoginHistoryDao loginHistoryDao) {
        this.customerAccountDao = customerAccountDao;
        this.customerDetailDao = customerDetailDao;
        this.chagePasswordDao = chagePasswordDao;
        this.loginHistoryDao = loginHistoryDao;
    }


    @Override
    public void registerCustomer(RegisterCustomerRequest registerCustomer) {
        customerDetailDao.findByIdentityNumberOrUsername(registerCustomer.getIdentityNumber(), registerCustomer.getUsername()).ifPresent(x ->{ throw new DataIntegrityViolationException("Duplicate data found");});

        CustomerAccount customerAccount = CustomerAccount.builder()
                .password(registerCustomer.getPassword())
                .username(registerCustomer.getUsername())
                .build();
        customerAccount =  customerAccountDao.save(customerAccount);

        CustomerDetail customerDetail = CustomerDetail.builder()
                .email(registerCustomer.getMail())
                .nationalIdentityNumber(registerCustomer.getIdentityNumber())
                .name(registerCustomer.getName())
                .birthDate(registerCustomer.getBirthDate())
                .account(customerAccount)
                .build();
       customerDetailDao.save(customerDetail);


    }

    @Override
    public void validateCustomer(ValidateCustomerRequest validateCustomer) {
        customerAccountDao.findByUsernameAndPassword(validateCustomer.getUsername(), validateCustomer.getPassword()).ifPresentOrElse(
                value -> this.loginHistoryDao.save(new LoginHistory(value.getUserId(), new Date(), "SUCCESS")),
                () -> {
                    throw new NoSuchElementException("invalid username or password");
        }
        );
    }

    @Override
    public ValidateExistingCustomerResponse requestChangePassword(ValidateExistingCustomerRequest validateExistingCustomerRequest) {

        CustomerDetail customerDetail = this.customerDetailDao.findByIdentityNumberAndUsername(validateExistingCustomerRequest.getNationalIdentityNumber(), validateExistingCustomerRequest.getUsername())
                .orElseThrow(() -> new NoSuchElementException("data Not Found"));
        String transactionId = this.chagePasswordDao.save(customerDetail.getAccount().getUserId());

        return new ValidateExistingCustomerResponse(transactionId, customerDetail.getAccount().getUserId());
    }

    @Override
    public void changePassword(ChangePasswordRequest resetPassword) {
        this.chagePasswordDao.findById(resetPassword.getTransactionId(), resetPassword.getUserId())
                .orElseThrow(() -> new NoSuchElementException("data Not Found"));
        customerAccountDao.updatePassword(resetPassword.getUserId(), resetPassword.getNewPassword());
    }
}
