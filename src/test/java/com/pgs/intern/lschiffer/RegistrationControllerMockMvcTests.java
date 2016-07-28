package com.pgs.intern.lschiffer;

import com.pgs.intern.NikoApplication;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Created by lschiffer on 7/25/2016.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = NikoApplication.class)
@WebAppConfiguration
@Transactional
public class RegistrationControllerMockMvcTests {
    @Autowired
    private WebApplicationContext context;

    private MockMvc mockMvc;

    @Before
    public void setup() {
        mockMvc = MockMvcBuilders
                .webAppContextSetup(context)
                .build();
    }

    @Test
    public void registrationTestWrongEmail() throws Exception {
        this.mockMvc.perform(
                post("/register")
                        .param("email", "wrongemail") // Wrong email.
                        .param("displayName", "Mockito")
                        .param("password", "password")
                        .param("passwordRepeat", "password")
        )
                .andExpect(status().isOk())
                .andExpect(view().name("/unauthorised/register")); // Same page with errors displayed.
    }

    @Test
    public void registrationTestCorrectData() throws Exception {
        this.mockMvc.perform(
                post("/register")
                        .param("email", "email@gmail.com")
                        .param("displayName", "Mockito")
                        .param("password", "password")
                        .param("passwordRepeat", "password") // Good form data.
        )
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/login")); // Success -> redirect to login page.
    }

    @Test
    public void authenticationBasedInterceptorTestProjectAddUnauthorisedGet() throws Exception {
        this.mockMvc.perform(
                get("/project/add")
        )
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("login")); // Unauthorised -> redirect to login page.
    }

    @Test
    public void authenticationBasedInterceptorTestProjectAddUnauthorisedPost() throws Exception {
        this.mockMvc.perform(
                post("/project/add")
        )
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("login")); // Unauthorised -> redirect to login page.
    }
}
