package com.pgs.intern.dao;

import com.pgs.intern.NikoApplication;
import com.pgs.intern.models.Project;
import com.pgs.intern.models.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.security.access.method.P;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import static org.junit.Assert.*;

/**
 * Created by Maciej Rosa on 7/26/2016 10:31 AM.
 */


@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = NikoApplication.class)
@WebAppConfiguration
public class ProjectDaoJpaTest {
    @Autowired
    private ProjectDaoJpa projectDaoJpa;

    @Autowired
    private UserDao userDao;

    @Test
    public void findProject(){
        Project project = projectDaoJpa.findByTitle("Project");
        System.out.println(project.getOwner().getDisplayName());
        assertEquals("He tooks it?",13,project.getProjectId());
        assertNull("It should be null",projectDaoJpa.findByTitle("This is not there"));
    }

    @Test
    public void checkIfProjectExists(){
        assertTrue("Existing project",projectDaoJpa.existsByTitle("Project"));
        assertFalse("Non-existing project",projectDaoJpa.existsByTitle("Non-existing project"));
    }

    @Test
    public void getAllOwnedProjects(){
        User owner = userDao.findUser("mail@mail.com");
        for(Project project : projectDaoJpa.findByOwnerOrderByTitleAsc(owner)){
            System.out.println(project.getTitle());
        }
    }
}