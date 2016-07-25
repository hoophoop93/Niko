package com.pgs.intern.mzalucka;

import com.pgs.intern.NikoApplication;
import com.pgs.intern.controllers.RegistrationController;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.mvc.method.annotation.ExceptionHandlerExceptionResolver;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Created by mzalucka on 25-Jul-16.
 */

@Transactional
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = NikoApplication.class)
@WebAppConfiguration
public class RegistrationControllerTest {

    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Autowired
    private RegistrationController registrationController;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(registrationController).setHandlerExceptionResolvers(new ExceptionHandlerExceptionResolver()).build();
    }

    @Test
    public void registerTest() throws Exception {

        mockMvc.perform(
                post("/register")
                        .param("email", "EMAIL@EMAIL.COM")
                        .param("displayName", "DISPLAYNAME")
                        .param("password", "PASSWORD")
                        .param("passwordRepeat", "PASSWORD")
        )
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/login"));
    }

    @Test
    public void registerTest1() throws Exception{

        mockMvc.perform(
                post("/register")
                .param("email", "EMAIL") //email is wrong
                .param("displayName", "DISPLAYNAME")
                .param("password", "PASSWORD")
                .param("passwordRepeated", "PASSWORD")
        )
                .andExpect(status().isOk())
                .andExpect(view().name("unauthorised/register"));
    }


}



