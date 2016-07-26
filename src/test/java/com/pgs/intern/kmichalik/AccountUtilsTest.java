package com.pgs.intern.kmichalik;

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
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

        String password = "test123";
        System.out.println("Password: " + password);
        String hash = passwordEncoder.encode(password);
        System.out.println("Hash: " + hash);

        assertNotEquals(password, hash);
        assertTrue(hash != null && hash.length() == 60);

    }

    @Test
    public void validatePassword() throws Exception {
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

        String password = "test123";
        String hash = passwordEncoder.encode(password);

        assertTrue(passwordEncoder.matches(password, hash));


    }

}