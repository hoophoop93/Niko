package com.pgs.intern.lschiffer;

import com.pgs.intern.dao.ProjectDao;
import com.pgs.intern.models.ProjectViewModel;
import com.pgs.intern.models.User;
import com.pgs.intern.services.CurrentUser;
import com.pgs.intern.services.ProjectService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.util.ReflectionUtils;

import static org.mockito.Matchers.anyLong;
import static org.mockito.Matchers.anyObject;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by lschiffer on 7/25/2016.
 */
public class ProjectServiceMockitoTests {
//    @Mock
//    ProjectService projectService;
//
//    @Rule
//    public MockitoRule mockitoRule = MockitoJUnit.rule();
//
//    @Test
//    public void addProjectTest() throws Exception {
//        ProjectViewModel projectViewModel = new ProjectViewModel();
//        projectViewModel.setTitle("Mockito Test Project");
//
//        projectService.addProject(projectViewModel);
//
//        verify(projectService).addProject(projectViewModel);
//    }
//
//    @Test
//    public void addUserForProjectTest() throws Exception {
//        projectService.addUserForProject(1L, 2L);
//
//        verify(projectService).addUserForProject(1L, 2L);
//    }

    @InjectMocks
    ProjectService projectService;

    @Mock
    ProjectDao projectDao;

    @Mock
    CurrentUser currentUser;

    @Before
    public void beforeEach() throws NoSuchFieldException {
//        projectDao = mock(ProjectDao.class);
//        ReflectionUtils.setField(ProjectService.class.getDeclaredField("projectDao"), projectService, projectDao);
//
//        currentUser = mock(CurrentUser.class);
//        ReflectionUtils.setField(ProjectService.class.getDeclaredField("currentUser"), projectService, currentUser);

        MockitoAnnotations.initMocks(this);
        User user = null; // ...

        when(currentUser.getUser()).thenReturn(user);
    }

    @Test
    public void testProjectWasAddedSucessfully() throws Exception {
        ProjectViewModel pvm = new ProjectViewModel();
        pvm.setTitle("TITLE");

        projectService.addProject(pvm);
        verify(projectDao).save(anyObject());
    }
}
