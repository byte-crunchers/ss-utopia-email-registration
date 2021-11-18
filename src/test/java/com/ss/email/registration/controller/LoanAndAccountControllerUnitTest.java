package com.ss.email.registration.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ss.email.registration.dto.AccountRegistrationRequest;
import com.ss.email.registration.dto.LoanRegistrationRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
public class LoanAndAccountControllerUnitTest {


    AccountRegistrationRequest accountRegistrationRequest = new AccountRegistrationRequest(
            "jimchao@email.com","Jim",1);

    AccountRegistrationRequest cardRegistrationRequest = new AccountRegistrationRequest(
            "jimchao@email.com","Jim",2);

    LoanRegistrationRequest loanRegistrationRequest = new LoanRegistrationRequest(
            "jimchao@email.com","Tim",1);

    MockMvc mvc;

    @Autowired
    WebApplicationContext wac;

    @BeforeEach
    void beforeEach() {
//        mvc = MockMvcBuilders.webAppContextSetup(wac).build();
        mvc = MockMvcBuilders
                .webAppContextSetup(wac)
                .apply(springSecurity())
                .build();

    }

//    @Test
//    void test_TokenCreation_withValidAccountRequest() throws Exception{
//        var mockDtoAsJson = new ObjectMapper().writeValueAsString(accountRegistrationRequest);
//        mvc.perform( post("http://localhost:8090/api/v1/signup/account")
//                .contentType(MediaType.APPLICATION_JSON)
//                .content(mockDtoAsJson))
//                .andExpect(status().is(200));
//    }

//    @Test
//    void test_TokenCreation_withValidLoanRequest() throws Exception{
//        var mockDtoAsJson = new ObjectMapper().writeValueAsString(loanRegistrationRequest);
//        mvc.perform( post("http://localhost:8090/api/v1/signup/loan")
//                .contentType(MediaType.APPLICATION_JSON)
//                .content(mockDtoAsJson))
//                .andExpect(status().is(200));
//    }

//    @Test
//    void test_TokenCreation_withValidCardAccountRequest() throws Exception{
//        var mockDtoAsJson = new ObjectMapper().writeValueAsString(cardRegistrationRequest);
//        mvc.perform( post("http://localhost:8090/api/v1/signup/card")
//                .contentType(MediaType.APPLICATION_JSON)
//                .content(mockDtoAsJson))
//                .andExpect(status().is(200));
//    }

}
