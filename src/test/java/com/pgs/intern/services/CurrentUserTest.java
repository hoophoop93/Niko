package com.pgs.intern.services;

import com.pgs.intern.dao.UserDao;
import com.pgs.intern.models.User;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.Assert.*;
import static org.mockito.Matchers.anyLong;
import static org.mockito.Mockito.*;

/**
 * Created by Maciej Rosa on 7/26/2016 9:20 AM.
 */
public class CurrentUserTest {

    @Mock
    UserDao userDao;

    @InjectMocks
    CurrentUser currentUser;

    private User user;

    @Before
    public void setUp() throws Exception {
        user = mock(User.class);
        currentUser = new CurrentUser();
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void getUser() throws Exception {
        assertNull("Class without user should return null",currentUser.getUser());
        when(userDao.findById(anyLong())).thenReturn(user);
        currentUser.setUser(user);
        assertEquals("Initiated class should return user",user,currentUser.getUser());
    }

    @Test
    public void isAuthenticated() throws Exception {
        currentUser = new CurrentUser();
        assertFalse("Class without user should return false",currentUser.isAuthenticated());
        currentUser.setUser(user);
        assertTrue("Initiated class should return true",currentUser.isAuthenticated());
    }

    @Test
    public void logOut() throws Exception {
        assertTrue(currentUser.isAuthenticated());
        currentUser.logOut();
        assertFalse(currentUser.isAuthenticated());
    }

}