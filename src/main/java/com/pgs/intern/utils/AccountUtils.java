package com.pgs.intern.utils;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * Created by lschiffer on 7/13/2016.
 */
public class AccountUtils {
    public static String getHashFor(String text) {
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String hashedPassword = passwordEncoder.encode(text);

        return hashedPassword;
    }

    public static boolean validatePassword(String providedPassword, String hashedPasswordFromDatabase) {
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

        return passwordEncoder.matches(providedPassword, hashedPasswordFromDatabase);
    }
}
