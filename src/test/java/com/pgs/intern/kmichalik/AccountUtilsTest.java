package com.pgs.intern.kmichalik;

import com.pgs.intern.utils.AccountUtils;
import org.junit.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

/**
 * Created by kmichalik on 7/25/2016 8:47 AM.
 */
public class AccountUtilsTest {

    @Test
    public void getHashFor() throws Exception {

        String password = "test123";
        System.out.println("Password: " + password);
        String hash = AccountUtils.getHashFor(password);
        System.out.println("Hash: "+hash);

        String password2 = "testtest123";
        String hash2 = AccountUtils.getHashFor(password2);
        System.out.println("Hash2: "+hash2);

        assertNotEquals(hash,hash2);
        assertNotEquals(password, hash);
        assertTrue(hash != null && hash.length() == 60);

    }

    @Test
    public void validatePassword() throws Exception {


        String password = "test123";
        String hash = AccountUtils.getHashFor(password);

        assertTrue(AccountUtils.validatePassword(password, hash));


    }

}