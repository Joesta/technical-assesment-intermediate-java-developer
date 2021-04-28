package com.github.joesta.cruddemo.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.joesta.cruddemo.CrudDemoApplicationTests;
import com.github.joesta.cruddemo.models.Customer;
import com.github.joesta.cruddemo.models.CustomerBuilder;
import com.github.joesta.cruddemo.repository.CustomerRepository;
import com.github.joesta.cruddemo.service.CustomerService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.After;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class CustomerResourceTest extends CrudDemoApplicationTests {

    private final Log log = LogFactory.getLog(this.getClass().getName());

    private MockMvc mvc;

    @MockBean
    private CustomerRepository customerRepository;

    @MockBean
    private CustomerService customerService;

    @Autowired
    private WebApplicationContext context;

    @Autowired
    private ObjectMapper objectMapper;

    private Customer customer;

    @Before
    public void setUp() {
        log.info("setUp() running in CustomerResourceTest");
        customer = CustomerBuilder.buildACustomer(1);
        mvc = MockMvcBuilders.webAppContextSetup(context).build();
    }

    @After
    public void tearDown() {
        log.info("tearDown() running in CustomerResourceTest");
        customer = null;
    }

    @Test
    public void putMappingForNewCustomer() throws Exception {
        this.mvc.perform(put("/customer/")
                .content(objectMapper.writeValueAsString(customer))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    public void postMappingForCustomer() throws Exception {
        when(customerRepository.save(any(Customer.class))).thenReturn(customer);
        this.mvc.perform(post("/customer/")
                .content(objectMapper.writeValueAsString(customer))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print());
        verify(customerService, times(1)).updateCustomer(any(Customer.class));
    }

    @Test
    public void deleteMappingForCustomer() throws Exception {
        when(customerService.deleteCustomer(anyString())).thenReturn(Optional.ofNullable(customer));
        this.mvc.perform(delete("/customer/" + customer.getCustomerNumber())
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    public void findMappingForCustomer() throws Exception {
        when(customerService.findByCustomerNumber(customer.getCustomerNumber())).thenReturn(Optional.of(customer));
        this.mvc.perform(get("/customer/" + customer.getCustomerNumber())
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print());
    }
}