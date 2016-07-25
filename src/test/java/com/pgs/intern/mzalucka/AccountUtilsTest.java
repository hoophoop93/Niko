package com.pgs.intern.mzalucka;

import com.pgs.intern.NikoApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import static com.pgs.intern.utils.AccountUtils.getHashFor;
import static com.pgs.intern.utils.AccountUtils.validatePassword;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Created by mzalucka on 25-Jul-16.
 */

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
        assertFalse(password.equals(password1));
    }

    @Test
    public void validatePasswordTest() {
        assertTrue(validatePassword("PASSWORD", "$2a$10$NUyXW0TNk7GrXfldCbjhdeDscG7Csi2Pv8IEE8N6/ATckCoar4MnO"));
    }


}