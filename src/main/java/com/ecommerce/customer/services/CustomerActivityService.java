package com.ecommerce.customer.services;

import com.ecommerce.customer.dto.*;
import com.ecommerce.customer.repo.custAccount.CustomerAccountDao;
import com.ecommerce.customer.repo.custAccount.CustomerAccountModel;
import com.ecommerce.customer.repo.custDetail.CustomerDetailDao;
import com.ecommerce.customer.repo.custDetail.CustomerDetailModel;
import com.ecommerce.customer.repo.loginHistory.LoginHistoryDao;
import com.ecommerce.customer.repo.loginHistory.LoginHistoryModel;
import com.ecommerce.customer.repo.session.SessionDao;
import com.ecommerce.customer.repo.session.SessionModel;
import com.ecommerce.customer.utils.CommonStatus;
import com.ecommerce.customer.utils.ParameterComponent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.NoSuchElementException;

@Service
@Slf4j
public class CustomerActivityService implements CustomerActivity {

    private final CustomerAccountDao customerAccountDao;
    private final CustomerDetailDao customerDetailDao;
    private final LoginHistoryDao loginHistoryDao;
    private final SessionDao sessionDao;
    private final ParameterComponent parameterComponent;


    public CustomerActivityService(CustomerAccountDao customerAccountDao, CustomerDetailDao customerDetailDao, LoginHistoryDao loginHistoryDao, SessionDao sessionDao, ParameterComponent parameterComponent) {
        this.customerAccountDao = customerAccountDao;
        this.customerDetailDao = customerDetailDao;
        this.loginHistoryDao = loginHistoryDao;
        this.sessionDao = sessionDao;
        this.parameterComponent = parameterComponent;
    }


    @Override
    public void register(RegisterCustomerRequest registerCustomer) {
        this.customerDetailDao.findByIdentityNumberOrUsername(registerCustomer.getIdentityNumber(), registerCustomer.getUsername()).ifPresent(x -> {
            throw new DataIntegrityViolationException("Duplicate data found");
        });

        CustomerAccountModel customerAccount = CustomerAccountModel.builder()
                .password(registerCustomer.getPassword())
                .username(registerCustomer.getUsername())
                .build();
        customerAccount = this.customerAccountDao.save(customerAccount);

        CustomerDetailModel customerDetail = CustomerDetailModel.builder()
                .email(registerCustomer.getMail())
                .nationalIdentityNumber(registerCustomer.getIdentityNumber())
                .name(registerCustomer.getName())
                .birthDate(registerCustomer.getBirthDate())
                .account(customerAccount)
                .build();
        this.customerDetailDao.save(customerDetail);


    }

    @Override
    public LoginResponse login(LoginRequest loginRequest) {
        CustomerAccountModel customerAccount = this.customerAccountDao.findByUsernameAndPassword(loginRequest.getUsername(), loginRequest.getPassword()).orElseThrow(() -> {
            throw new NoSuchElementException("invalid username or password");
        });

        this.logout(new LogoutRequest(customerAccount.getUserId()));

        String sessionId = this.sessionDao.save(customerAccount.getUserId());

        this.loginHistoryDao.save(new LoginHistoryModel(customerAccount.getUserId(), new Date(), CommonStatus.SUCCESS.name()));

        return new LoginResponse(sessionId, customerAccount.getUserId());
    }

    @Override
    public void logout(LogoutRequest logoutRequest) {
        this.sessionDao.delete(logoutRequest.getUserId());
    }

    @Override
    public void validateSession(ValidateSessionRequest validateSessionRequest) {
        SessionModel session = this.sessionDao.findById(validateSessionRequest.getSession()).orElseThrow(() -> {
            throw new NoSuchElementException("invalid Session");
        });
        Date date = new Date();

        Long time = session.getDate().getTime();
        Long currentTime = date.getTime();

        if (currentTime - time > parameterComponent.getSessionTimeout()) {
            throw new NoSuchElementException("invalid Session");
        } else {
            this.sessionDao.updateDate(validateSessionRequest.getUserId(), validateSessionRequest.getSession());
        }
    }


}
