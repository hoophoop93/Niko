package com.pgs.intern.services;

import com.pgs.intern.dao.ProjectDao;
import com.pgs.intern.models.Mood;
import com.pgs.intern.models.Project;
import com.pgs.intern.models.ProjectViewModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

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

    public void addProject(ProjectViewModel projectViewModel){
        Project project = new Project();
        project.setTitle(projectViewModel.getTitle());
        project.setOwner(currentUser.getUser());
        projectDao.save(project);
    }

    public List<Project> getProjectsOfCurrentUser() {
        return projectDao.getSortedOwnedProjects(currentUser.getUser());
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
}
