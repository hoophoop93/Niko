package com.pgs.intern.services;

import com.pgs.intern.dao.ProjectDao;
import com.pgs.intern.dao.UserDao;
import com.pgs.intern.models.Mood;
import com.pgs.intern.models.Project;
import com.pgs.intern.models.ProjectViewModel;
import com.pgs.intern.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Maciej Rosa on 7/14/2016 12:44 PM.
 */
@Component
public class ProjectService {
    @Inject
    private CurrentUser currentUser;

    @Autowired
    private ProjectDao projectDao;
    @Autowired
    private UserDao userDao;

    public void addProject(ProjectViewModel projectViewModel) {
        Project project = new Project();
        project.setTitle(projectViewModel.getTitle());
        project.setOwner(currentUser.getUser());
        projectDao.save(project);
    }

    public List<Project> getProjectsOfCurrentUser() {
        return projectDao.getUserProjects(currentUser.getUser());
    }

    public Map<Long, String> getBlockedDatesInProjects() {
        Map<Long, String> blockedDatesInProjects = new HashMap<>();

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");

        for (Project p : getProjectsOfCurrentUser()) {
            List<Mood> moodList = projectDao.getMoodList(currentUser.getUser(), p);

            StringBuilder blockedDates = new StringBuilder();
            blockedDates.append("[");

            List<String> datesList = new ArrayList<>();
            for (Mood m : moodList) {
                datesList.add("\"" + simpleDateFormat.format(m.getDateAdd()) + "\"");
            }

            blockedDates.append(String.join(",", datesList));

            blockedDates.append("]");

            blockedDatesInProjects.put(p.getProjectId(), blockedDates.toString());
        }

        return blockedDatesInProjects;
    }

    @Transactional
    public void addUserForProject(Long idUser, Long projectId) throws Exception {
        Project project = projectDao.findById(projectId);
        User user = userDao.findById(idUser);
        if (!currentUser.isAuthenticated()) {
            throw new Exception("You are not logged in.");
        }
        if (project == null) {
            throw new Exception("Project not found.");
        }
        if (user == null) {
            throw new Exception("User not found.");
        }
        if (project.getJoinedUsersList().contains(user)) {
            throw new Exception("User already added.");
        }
        if (!project.getOwner().equals(currentUser.getUser())) {
            throw new Exception("You are not project owner.");
        }
        project.getJoinedUsersList().add(user);
    }
}
