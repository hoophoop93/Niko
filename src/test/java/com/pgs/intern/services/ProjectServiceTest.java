package com.pgs.intern.kmichalik;

import com.pgs.intern.dao.ProjectDao;
import com.pgs.intern.dao.ProjectDaoJpa;
import com.pgs.intern.models.Project;
import com.pgs.intern.models.ProjectViewModel;
import com.pgs.intern.models.User;
import com.pgs.intern.services.CurrentUser;
import com.pgs.intern.services.ProjectService;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import static org.mockito.Mockito.*;


/**
 * Created by kmichalik on 7/25/2016 9:28 AM.
 */
public class ProjectServiceTest {

    @InjectMocks
    public ProjectService projectService;
    @Mock
    public ProjectService projectService2;
    @Rule
    public MockitoRule mockitoRule = MockitoJUnit.rule();
    @Mock
    User user = new User();
    @Mock
    Project project = new Project();
    @Mock
    ProjectDao projectDao;
    @Mock
    CurrentUser currentUser;
    @Mock
    ProjectDaoJpa projectDaoJpa;

    @Before
    public void beforeEach() throws NoSuchFieldException {

        MockitoAnnotations.initMocks(this);
        User user = null;

        when(currentUser.getUser()).thenReturn(user);
    }

    @Test
    public void addProject() throws Exception {

        ProjectViewModel projectViewModel = new ProjectViewModel();
        projectViewModel.setTitle("ProjektTest");
        projectService.addProject(projectViewModel);
        verify(projectDaoJpa).save((Project) anyObject());
    }

    @Test
    public void addUserForProject() throws Exception {

        when(user.getIdUser()).thenReturn(1L);
        when(project.getProjectId()).thenReturn(2L);

        projectService2.addUserForProject(user.getIdUser(), project.getProjectId());
        verify(projectService2).addUserForProject(user.getIdUser(), project.getProjectId());

    }

}