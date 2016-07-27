package com.pgs.intern.mzalucka;

import com.pgs.intern.NikoApplication;
import com.pgs.intern.dao.ProjectDao;
import com.pgs.intern.dao.ProjectDaoJpa;
import com.pgs.intern.dao.UserDao;
import com.pgs.intern.models.Project;
import com.pgs.intern.models.ProjectViewModel;
import com.pgs.intern.models.User;
import com.pgs.intern.services.CurrentUser;
import com.pgs.intern.services.ProjectService;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.anyObject;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by mzalucka on 25-Jul-16.
 */

/*******************************Mockito_Tests********************/

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = NikoApplication.class)
@WebAppConfiguration
public class ProjectServiceTest {

    @InjectMocks
    ProjectService projectService;

    @Mock
    CurrentUser currentUser;

    @Mock
    ProjectDaoJpa projectDaoJpa;

    @Rule
    public MockitoRule mockitoRule = MockitoJUnit.rule();

    @Test
    public void contextLoads() {
    }


    @Before
    public void beforeEach() throws NoSuchFieldException {

        MockitoAnnotations.initMocks(this);
        User user = null;

        when(currentUser.getUser()).thenReturn(user);
    }

    @Test
    public void addProjectTest() {

        ProjectViewModel projectViewModel = new ProjectViewModel();
        projectViewModel.setTitle("TITLE");

        projectService.addProject(projectViewModel);

        verify(projectDaoJpa).save((Project) anyObject());
    }

    @Test
    public void addUserForProjectTest() throws Exception {

    }
}