package com.pgs.intern.services;

import com.google.common.collect.ImmutableMap;
import com.pgs.intern.dao.ProjectDao;
import com.pgs.intern.dao.ProjectDaoJpa;
import com.pgs.intern.dao.UserDao;
import com.pgs.intern.dao.UserRepository;
import com.pgs.intern.models.Project;
import com.pgs.intern.models.ProjectViewModel;
import com.pgs.intern.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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
    UserRepository userRepository;

    @Autowired
    ProjectDaoJpa projectDaoJpa;


    public void addProject(ProjectViewModel projectViewModel) {
        Project project = new Project();
        project.setTitle(projectViewModel.getTitle());
        project.setOwner(currentUser.getUser());
        projectDaoJpa.save(project);
    }

    public List<Project> getProjectsOfCurrentUser() {
        return projectDao.getUserProjects(currentUser.getUser());
    }

    public Map<Long, List<Map<String, String>>> getBlockedDatesInProjectsJsonStream() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");

        return currentUser.getUser().getMoodList()
                .stream()
                .collect(
                        Collectors.groupingBy(m -> m.getProject().getProjectId(),
                                Collectors.mapping(
                                        m -> (Map<String, String>) ImmutableMap.of(
                                                    "date", simpleDateFormat.format(m.getDateAdd()),
                                                    "mood", m.getMoodType().getLowerCase()
                                            ),
                                        Collectors.toList()
                                )
                        )
                );
    }

    @Transactional
    public void addUserForProject(Long idUser, Long projectId) throws Exception {
        Project project = projectDao.findById(projectId);
        User user = userRepository.findByIdUser(idUser);
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
