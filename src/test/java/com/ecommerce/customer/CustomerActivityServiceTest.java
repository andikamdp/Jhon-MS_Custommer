package com.ecommerce.customer;

import com.ecommerce.customer.dto.RegisterCustomerRequest;
import com.ecommerce.customer.repo.custAccount.CustomerAccountDao;
import com.ecommerce.customer.repo.custAccount.CustomerAccountModel;
import com.ecommerce.customer.repo.custAccount.CustomerAccountRepository;
import com.ecommerce.customer.repo.custDetail.CustomerDetailDao;
import com.ecommerce.customer.repo.custDetail.CustomerDetailModel;
import com.ecommerce.customer.repo.loginHistory.LoginHistoryDao;
import com.ecommerce.customer.repo.session.SessionDao;
import com.ecommerce.customer.services.CustomerActivityService;
import com.ecommerce.customer.utils.ParameterComponent;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class CustomerActivityServiceTest {

    @InjectMocks
    CustomerActivityService service;
    @Mock
    CustomerAccountDao customerAccountDao;
    @Mock
    CustomerDetailDao customerDetailDao;
    @Mock
    LoginHistoryDao loginHistoryDao;
    @Mock
    SessionDao sessionDao;
    @Mock
    ParameterComponent parameterComponent;

    @Autowired
    CustomerAccountRepository customerAccountRepository;

    @Test
    public void testCreateOrSaveEmployee() {

        RegisterCustomerRequest customerData = new RegisterCustomerRequest();
        customerData.setUsername(RandomGenerator.StringGenerator());
        customerData.setPassword(RandomGenerator.StringGenerator());
        customerData.setName(RandomGenerator.StringGenerator());
        customerData.setMail(RandomGenerator.StringGenerator());
        customerData.setIdentityNumber(RandomGenerator.StringGenerator());
        customerData.setBirthDate(RandomGenerator.DateGenerator());

        CustomerAccountModel customerAccount = CustomerAccountModel.builder()
                .password(customerData.getPassword())
                .username(customerData.getUsername())
                .build();

        when(customerAccountDao.save(customerAccount)).thenReturn(customerAccount);

        service.register(customerData);

        verify(customerDetailDao, times(1)).findByIdentityNumberOrUsername(customerData.getIdentityNumber(), customerData.getUsername());

        verify(customerAccountDao, times(1)).save(customerAccount);

        CustomerDetailModel customerDetail = CustomerDetailModel.builder()
                .email(customerData.getMail())
                .nationalIdentityNumber(customerData.getIdentityNumber())
                .name(customerData.getName())
                .birthDate(customerData.getBirthDate())
                .account(customerAccount)
                .build();

        verify(customerDetailDao, times(1)).save(customerDetail);
    }

    @Test
    public void testCreateOrSaveEmployeeDuplicate() {

        RegisterCustomerRequest customerData = new RegisterCustomerRequest();
        customerData.setUsername(RandomGenerator.StringGenerator());
        customerData.setPassword(RandomGenerator.StringGenerator());
        customerData.setName(RandomGenerator.StringGenerator());
        customerData.setMail(RandomGenerator.StringGenerator());
        customerData.setIdentityNumber(RandomGenerator.StringGenerator());
        customerData.setBirthDate(RandomGenerator.DateGenerator());

        CustomerAccountModel customerAccount = CustomerAccountModel.builder()
                .password(customerData.getPassword())
                .username(customerData.getUsername())
                .build();

        CustomerDetailModel customerDetail = CustomerDetailModel.builder()
                .email(customerData.getMail())
                .nationalIdentityNumber(customerData.getIdentityNumber())
                .name(customerData.getName())
                .birthDate(customerData.getBirthDate())
                .account(customerAccount)
                .build();

        Optional <CustomerDetailModel> registerCustomerRequest = Optional.of(customerDetail);
        when(customerDetailDao.findByIdentityNumberOrUsername(customerData.getIdentityNumber(), customerData.getUsername())).thenReturn(registerCustomerRequest);
//        when(customerAccountDao.save(customerAccount)).thenReturn(customerAccount);


        assertThrows(DataIntegrityViolationException.class, () -> {
            service.register(customerData);
        });
    }
}
