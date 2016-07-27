package com.pgs.intern.mzalucka;

import com.pgs.intern.NikoApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import static com.pgs.intern.utils.AccountUtils.getHashFor;
import static com.pgs.intern.utils.AccountUtils.validatePassword;
import static org.junit.Assert.*;

/**
 * Created by mzalucka on 25-Jul-16.
 */

/*******************************JUnit_Tests********************/

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = NikoApplication.class)
@WebAppConfiguration
public class AccountUtilsTest {

    @Test
    public void contextLoads() {
    }

    @Test
    public void accountUtilsTest() {
        String password = getHashFor("PASSWORD");
        String password1 = getHashFor("PASSWORD");
        assertNotEquals(password,password1);
    }

    @Test
    public void validatePasswordTest() {
        assertTrue(validatePassword("PASSWORD", "$2a$10$NUyXW0TNk7GrXfldCbjhdeDscG7Csi2Pv8IEE8N6/ATckCoar4MnO"));
    }

    @Test
    public void accountUtilsTestPasswordLength(){
        String pass = "PASS";
        String password = "PASSWORD";

        assertTrue("Password is too short", pass.length()<8);
        assertTrue("Password has 8 characters", password.length()==8);
        assertFalse("Password is not empty", pass.isEmpty());
    }

}