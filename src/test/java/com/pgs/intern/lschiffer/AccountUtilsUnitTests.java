package com.pgs.intern.lschiffer;

import com.pgs.intern.utils.AccountUtils;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by lschiffer on 7/25/2016.
 */
public class AccountUtilsUnitTests {
    @Test
    public void hashingTest() throws Exception {
        String hashA = AccountUtils.getHashFor("hashA");
        String hashB = AccountUtils.getHashFor("hashB");

        String sameString = "sameString";
        String sameHashA = AccountUtils.getHashFor(sameString);
        String sameHashB = AccountUtils.getHashFor(sameString);

        assertEquals("All hashes should have the same length.", hashA.length(), hashB.length());

        assertEquals("All hashes should have length = 60.", hashA.length(), 60);

        assertNotEquals("Hashes for the same text should be different.", sameHashA, sameHashB);

        assertNotEquals("Hash of hashed string shouldn't be original string.", "hashA", AccountUtils.getHashFor(AccountUtils.getHashFor("hashA")));
    }

    @Test
    public void hashingValidationTest() throws Exception {
        String password = "password";

        String hash = AccountUtils.getHashFor(password);

        String hashAgain = AccountUtils.getHashFor(password);

        assertTrue("Validation of hash for the same text should return TRUE.", AccountUtils.validatePassword(password, hash));

        assertTrue("Validation of hash with different, random salt for the same text should return TRUE.", AccountUtils.validatePassword(password, hashAgain));

        assertFalse("Validation of hash for different text should return FALSE.", AccountUtils.validatePassword((password + password), hash));

        assertFalse("Validation of hash for lower case text changed to upper case should return FALSE.", AccountUtils.validatePassword(password.toUpperCase(), hash));
    }
}
