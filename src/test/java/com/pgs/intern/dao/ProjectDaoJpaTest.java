package com.pgs.intern.dao;

import com.pgs.intern.NikoApplication;
import com.pgs.intern.models.Project;
import com.pgs.intern.models.User;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.security.access.method.P;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;

/**
 * Created by Maciej Rosa on 7/26/2016 10:31 AM.
 */


@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = NikoApplication.class)
@WebAppConfiguration
@Transactional
public class ProjectDaoJpaTest {

    @Autowired
    private ProjectDaoJpa projectDaoJpa;

    @Autowired
    private UserRepository userRepository;

    private Project project;
    private User user;


    @Test
    public void findProject(){
        project = projectDaoJpa.findByTitle("Test Projekt1");
        System.out.println(project.getOwner().getDisplayName());
        assertEquals("Check if project has that owner","user1@test.pl",project.getOwner().getEmail());
        assertNull("It should be null",projectDaoJpa.findByTitle("Non-existing project"));
    }

    @Test
    public void checkIfProjectExists(){
        assertTrue("Existing project",projectDaoJpa.existsByTitle("Test Projekt1"));
        assertFalse("Non-existing project",projectDaoJpa.existsByTitle("Non-existing project"));
    }

    @Test
    public void checkAddingProject(){
        Project project = new Project();
        User owner = userRepository.findByEmail("user1@test.pl");
        project.setTitle("@@TestProject");
        project.setOwner(owner);
        projectDaoJpa.save(project);
        assertTrue("New added project exists.",projectDaoJpa.existsByTitle("@@TestProject"));
    }

    @Test
    public void getUserProjects(){
        user = userRepository.findByEmail("user1@test.pl");
        project = projectDaoJpa.findByTitle("Test Projekt1");
        assertNotNull("User has some projects.", projectDaoJpa.getUserProjects(user));
        assertTrue("User belong to Test Projekt1", projectDaoJpa.getUserProjects(user).contains(project));
    }

    @Test
    public void getNoneJoinedUsersById(){
        user = userRepository.findByEmail("user1@test.pl");
        project = projectDaoJpa.findByTitle("Test Projekt1");
        Long projectId = project.getProjectId();
        assertNotNull("Someone belong to this project.", projectDaoJpa.getNoneJoinedUsersById(projectId));
        assertFalse("User already belong to Test Projekt1.", projectDaoJpa.getNoneJoinedUsersById(projectId).contains(user));
    }

}