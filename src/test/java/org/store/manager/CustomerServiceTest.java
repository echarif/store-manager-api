package org.store.manager;

/**
 * Created by hasan on 15/05/2018.
 */

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.store.manager.model.Customer;
import org.store.manager.service.CustomerService;

import java.util.Collection;
import java.util.Collections;

@RunWith(SpringRunner.class)
@WebMvcTest
public class CustomerServiceTest extends AbstractTest {

    @Autowired
    private CustomerService customerService;

    @Autowired
    MockMvc mockMvc;

    @MockBean
    CustomerService customer;

    @Before
    public void setUp() {
        //customerService.getAllCustomers();
    }

    @After
    public void tearDown() {
        //customerService.getAllCustomers();
    }

    @Test
    public void findAllTest() throws Exception {

        Mockito.when(customer.getAllCustomers()).thenReturn(
                Collections.emptyList()
        );
        MvcResult mvcResult = mockMvc.perform(
                MockMvcRequestBuilders.get("/customers/")
                        .accept(MediaType.APPLICATION_JSON)
        ).andReturn();

        System.out.println(mvcResult.getResponse());
        Mockito.verify(customer).getAllCustomers();
       /* Collection<Customer> list=customerService.getAllCustomers();
        Assert.assertNotNull("Failure, not null expected",list);
        Assert.assertEquals("Failure, not null expected",5,list.size());*/
    }

}
