package com.pgs.intern.controllers;

import com.pgs.intern.dao.ProjectDao;
import com.pgs.intern.dao.UserDao;
import com.pgs.intern.models.Mood;
import com.pgs.intern.models.Project;
import com.pgs.intern.models.ProjectViewModel;
import com.pgs.intern.models.User;
import com.pgs.intern.services.CurrentUser;
import com.pgs.intern.services.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.inject.Inject;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Maciej Rosa on 7/13/2016 3:26 PM.
 */
@Controller
public class ProjectController {

    @Autowired
    private ProjectService projectService;

    @Autowired
    private ProjectDao projectDao;

    @Autowired
    private UserDao userDao;

    @Inject
    CurrentUser currentUser;

    @RequestMapping(value = "/project/add", method = RequestMethod.GET)
    public ModelAndView addProject() {
        ModelAndView modelAndView = new ModelAndView();

        if (!currentUser.isAuthenticated()) {
            modelAndView.setViewName("redirect:/login");
            return modelAndView;
        }

        modelAndView.setViewName("authorised/projectadd");
        modelAndView.addObject("model", new ProjectViewModel());

        return modelAndView;
    }

    @RequestMapping(value = "/project/add", method = RequestMethod.POST)
    public ModelAndView addProjectPost(@Valid @ModelAttribute("model") ProjectViewModel model,
                                       final BindingResult result, final RedirectAttributes redirectAttributes) {
        ModelAndView modelAndView = new ModelAndView();

        modelAndView.addObject("model", model);

        if (!currentUser.isAuthenticated()) {
            modelAndView.setViewName("redirect:/login");
            return modelAndView;
        }
        if (projectDao.checkProjectTitle(model.getTitle())) {
            result.reject("error.projectAlreadyAdded", "This project name was taken.");
            modelAndView.setViewName("authorised/projectadd");
            return modelAndView;
        }

        if (result.hasErrors()) {
            modelAndView.setViewName("authorised/projectadd");
            return modelAndView;
        }

        projectService.addProject(model);


        modelAndView = new ModelAndView("redirect:/");

        List<String> infoMessages = new ArrayList<>();
        infoMessages.add("Added project '" + model.getTitle() + "' successfully!");

        redirectAttributes.addFlashAttribute("infos", infoMessages);

        return modelAndView;
    }

    @RequestMapping(value = "/project", method = RequestMethod.GET)
    public ModelAndView viewProjects() {
        if (!currentUser.isAuthenticated()) {
            return new ModelAndView("redirect:/login");
        }

        return new ModelAndView("authorised/projects", "projects", projectDao.getSortedOwnedProjects(currentUser.getUser()));
    }


    @ResponseBody
    @RequestMapping(value = "/project/{projectid}/getavailableusers", method = RequestMethod.GET)
    public Map<Long, String> getAvailableUsersByProjectId(@PathVariable long projectid) {
        Map<Long, String> availableUsers = new HashMap<>();
        if (!currentUser.isAuthenticated()) {
            return new HashMap<>();
        }
        for (User u : projectDao.getNoneJoinedUsersById(projectid)) {
            availableUsers.put(u.getIdUser(), u.getDisplayName() + " (" + u.getEmail() + ")");
        }
        return availableUsers;
    }

    @ResponseBody
    @RequestMapping(value = "/project/{projectids}/addusertoproject/{userids}", method = RequestMethod.GET)
    public Map<String, String> addAvailableUserToProject(@PathVariable String projectids, @PathVariable String userids) {
        Map<String, String> message = new HashMap<>();
        try {
            long userid = Long.parseLong(userids);
            User user = userDao.findById(userid);
            long projectid = Long.parseLong(projectids);
            Project project = projectDao.findById(projectid);
            projectService.addUserForProject(userid, projectid);
            message.put("success","User <strong>" + user.getDisplayName() + "</strong> is now in <strong>" + project.getTitle() + "</strong> project.");
            return message;
        } catch (NumberFormatException nfe) {
            message.put("error","Invalid data.");
            return message;
        } catch (Exception ex) {
            message.put("error",ex.getMessage());
            return message;
        }
    }
}


