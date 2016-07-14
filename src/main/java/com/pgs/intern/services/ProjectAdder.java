package com.pgs.intern.services;

import com.pgs.intern.dao.ProjectDao;
import com.pgs.intern.models.Project;
import com.pgs.intern.models.ProjectViewModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.inject.Inject;

/**
 * Created by Maciej Rosa on 7/14/2016 12:44 PM.
 */
@Component
public class ProjectAdder {

    @Inject
    private CurrentUser currentUser;

    @Autowired
    private ProjectDao projectDao;

    public void addProject(ProjectViewModel projectViewModel){
        Project project = new Project();
        project.setTitle(projectViewModel.getTitle());
        project.setOwner(currentUser.getUser());
        projectDao.save(project);
    }
}
