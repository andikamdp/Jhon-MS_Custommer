package com.ecommerce.customer.repo;

import com.ecommerce.customer.RandomGenerator;
import com.ecommerce.customer.repo.custAccount.CustomerAccountModel;
import com.ecommerce.customer.repo.custAccount.CustomerAccountRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(SpringExtension.class)
@DataJpaTest
public class CustAccountRepoTest {
    @Autowired
    CustomerAccountRepository customerAccountRepository;

    @Test
    public void testCreateReadDelete() {
        CustomerAccountModel customerAccountModel = new CustomerAccountModel();
        customerAccountModel.setPassword(RandomGenerator.StringGenerator());
        customerAccountModel.setUsername(RandomGenerator.StringGenerator());

        customerAccountRepository.save(customerAccountModel);

        Iterable<CustomerAccountModel> employees = customerAccountRepository.findAll();
        Assertions.assertThat(employees).extracting(CustomerAccountModel::getUserId).containsOnly(customerAccountModel.getUserId());

        customerAccountRepository.deleteAll();
        Assertions.assertThat(customerAccountRepository.findAll()).isEmpty();
    }

    @Test
    public void testDuplicateUsername() {
        String dummyValue = RandomGenerator.StringGenerator();
        CustomerAccountModel customerAccountModel = new CustomerAccountModel();
        customerAccountModel.setPassword(dummyValue);
        customerAccountModel.setUsername(dummyValue);

        customerAccountRepository.saveAndFlush(customerAccountModel).getUserId();

        CustomerAccountModel customerAccountModelDuplicate = new CustomerAccountModel();
        customerAccountModelDuplicate.setPassword(dummyValue);
        customerAccountModelDuplicate.setUsername(dummyValue);


        assertThrows(DataIntegrityViolationException.class, () -> {
            customerAccountRepository.saveAndFlush(customerAccountModelDuplicate);
        });
    }
}
