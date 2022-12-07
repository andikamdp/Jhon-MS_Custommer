package com.ecommerce.customer.services;

import com.ecommerce.customer.dto.ChangePasswordRequest;
import com.ecommerce.customer.dto.DetailCustomerResponse;
import com.ecommerce.customer.dto.ValidateExistingCustomerRequest;
import com.ecommerce.customer.dto.ValidateExistingCustomerResponse;
import com.ecommerce.customer.repo.changePassword.ChagePasswordDao;
import com.ecommerce.customer.repo.changePassword.ChangePasswordModel;
import com.ecommerce.customer.repo.custAccount.CustomerAccountDao;
import com.ecommerce.customer.repo.custDetail.CustomerDetailDao;
import com.ecommerce.customer.repo.custDetail.CustomerDetailModel;
import com.ecommerce.customer.utils.CommonStatus;
import com.ecommerce.customer.utils.ParameterComponent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.NoSuchElementException;

@Service
@Slf4j
public class CustomerManagingService implements CustomerManaging {

    private final CustomerAccountDao customerAccountDao;
    private final CustomerDetailDao customerDetailDao;
    private final ChagePasswordDao chagePasswordDao;
    private final ParameterComponent parameterComponent;

    public CustomerManagingService(CustomerAccountDao customerAccountDao, CustomerDetailDao customerDetailDao, ChagePasswordDao chagePasswordDao, ParameterComponent parameterComponent) {
        this.customerAccountDao = customerAccountDao;
        this.customerDetailDao = customerDetailDao;
        this.chagePasswordDao = chagePasswordDao;
        this.parameterComponent = parameterComponent;
    }


    @Override
    public DetailCustomerResponse detailCustomer(String userId) {
        CustomerDetailModel customerDetail = customerDetailDao.findByAccount_UserId(Long.parseLong(userId)).orElseThrow(() -> new NoSuchElementException("data Not Found"));
        DetailCustomerResponse detailCustomerResponse = new DetailCustomerResponse();
        detailCustomerResponse.setName(customerDetail.getName());
        detailCustomerResponse.setEmail(customerDetail.getEmail());
        detailCustomerResponse.setBirthDate(customerDetail.getBirthDate());
        detailCustomerResponse.setUsername(customerDetail.getAccount().getUsername());
        detailCustomerResponse.setUserId(customerDetail.getAccount().getUserId());

        return detailCustomerResponse;
    }

    @Override
    public ValidateExistingCustomerResponse requestChangePassword(ValidateExistingCustomerRequest validateExistingCustomerRequest) {
        CustomerDetailModel customerDetail = this.customerDetailDao.findByIdentityNumberAndUsername(validateExistingCustomerRequest.getNationalIdentityNumber(), validateExistingCustomerRequest.getUsername())
                .orElseThrow(() -> new NoSuchElementException("data Not Found"));

        this.chagePasswordDao.findById(customerDetail.getAccount().getUserId()).ifPresent(value -> {
            this.chagePasswordDao.updateStatus(value.getTransactionId(), value.getUserId(), CommonStatus.USED);
        });
        
        String transactionId = this.chagePasswordDao.save(customerDetail.getAccount().getUserId());

        return new ValidateExistingCustomerResponse(transactionId, customerDetail.getAccount().getUserId());
    }

    @Override
    public void changePassword(ChangePasswordRequest resetPassword) {
        ChangePasswordModel changePasswordKey = this.chagePasswordDao.findById(resetPassword.getTransactionId(), resetPassword.getUserId())
                .orElseThrow(() -> new NoSuchElementException("data Not Found"));
        this.chagePasswordDao.updateStatus(resetPassword.getTransactionId(), resetPassword.getUserId(), CommonStatus.USED);

        Date date = new Date();

        Long time = changePasswordKey.getDate().getTime();
        Long currentTime = date.getTime();

        if (currentTime - time > parameterComponent.getForgotPasswordTokenTimeout()) {
            throw new NoSuchElementException("invalid Session");
        }

        customerAccountDao.updatePassword(resetPassword.getUserId(), resetPassword.getNewPassword());
    }
}
