package com.pgs.intern.kmichalik;


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
 * Created by kmichalik on 7/25/2016 10:59 AM.
 */

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = NikoApplication.class)
@WebAppConfiguration
@Transactional
public class RegistrationControllerTest {

    @Autowired
    private WebApplicationContext context;

    private MockMvc mockMvc;

    @Before
    public void setup() {
        mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
    }


    @Test
    public void registerPost() throws Exception {

        mockMvc.perform(post("/register")
                .param("email", "tesTT@wp.pl")
                .param("displayName", "Test Test Test")
                .param("password", "password")
                .param("passwordRepeat", "password"))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/login"));

    }

    @Test
    public void registerGet() throws Exception {
        mockMvc.perform(
                get("/project"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("login"));

    }

    @Test
    public void registerPost2() throws Exception {
        mockMvc.perform(
                post("/project/add"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("login"));

    }

}