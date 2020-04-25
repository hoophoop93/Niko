package com.pgs.intern.dao;

import com.pgs.intern.NikoApplication;
import com.pgs.intern.models.Project;
import com.pgs.intern.models.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.Assert.*;

/**
 * Created by kmichalik on 7/27/2016 2:52 PM.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = NikoApplication.class)
@WebAppConfiguration
@Transactional
public class UserRepositoryTest {

    @Autowired
    UserRepository userRepository;
    @Autowired
    ProjectDaoJpa projectDaoJpa;

    @Test
    public void findByEmail() throws Exception {

        String email = "user1@test.pl";
        String dispalyName = "Test User1";
        User user = userRepository.findByEmail("user1@test.pl");
        assertEquals("Check whether the email of the project agrees with the stated ", user.getEmail(), "user1@test.pl");
        assertEquals("Check whether the dispalyName of the project agrees with the stated", user.getDisplayName(), "Test User1");
    }

    @Test
    public void findByIdUser() throws Exception {
        String email = "user1@test.pl";
        String dispalyName = "Test User1";
        User user = userRepository.findByEmail("user1@test.pl");
        System.out.println(user.getIdUser());
        User user2 = userRepository.findByIdUser(user.getIdUser());
        assertEquals("Check whether the email of the project agrees with the stated ", user2.getEmail(), email);
        assertEquals("Check whether the dispalyName of the project agrees with the stated", user2.getDisplayName(), dispalyName);

    }

    @Test
    public void checkByEmail() throws Exception {

        boolean result = userRepository.checkByEmail("user1@test.pl");
        assertTrue("If email is correct return true", result);

        boolean result2 = userRepository.checkByEmail("userW1@test.pl");
        assertFalse("If email is incorrect return false", result2);

    }

    @Test
    public void searchUserInProject() throws Exception {

        User user = userRepository.findByEmail("user2@test.pl");
        Project project = projectDaoJpa.findByTitle("Test Project1");

        boolean result = userRepository.searchUserInProject(user.getIdUser(), project.getProjectId());
        assertTrue("If user contains in project return true", result);

        User user2 = userRepository.findByEmail("user1@test.pl");
        Project project2 = projectDaoJpa.findByTitle("Test Project1");
        boolean result2 = userRepository.searchUserInProject(user2.getIdUser(), project2.getProjectId());

        assertFalse("If user not contains in project return false", result2);

    }

}