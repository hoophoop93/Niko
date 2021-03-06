package com.pgs.intern.controllers;

import com.pgs.intern.dao.MoodDaoDataJpaInterface;
import com.pgs.intern.dao.ProjectDao;
import com.pgs.intern.models.MoodViewModel;
import com.pgs.intern.models.Project;
import com.pgs.intern.services.CurrentUser;
import com.pgs.intern.services.MoodService;
import com.pgs.intern.services.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.inject.Inject;
import javax.validation.Valid;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Maciej Rosa on 7/14/2016 1:48 PM.
 */
@Controller
public class MoodController {
    @Inject
    private CurrentUser currentUser;

    @Autowired
    private ProjectDao projectDao;

    @Autowired
    private MoodService moodService;

    @Autowired
    private ProjectService projectService;

    @Autowired
    private MoodDaoDataJpaInterface moodDao;


    @RequestMapping(value = "/mood/add", method = RequestMethod.GET)
    public ModelAndView addMood() {
        MoodViewModel mood = new MoodViewModel();

        mood.setDateAdd(new Date());
        mood.setProjects(projectService.getProjectsOfCurrentUser());
        mood.setBlockedDatesInProjects(projectService.getBlockedDatesInProjectsJsonStream());

        return new ModelAndView("/authorised/moodadd", "model", mood);
    }

    @RequestMapping(value = "/mood/add", method = RequestMethod.POST)
    public ModelAndView addMoodPost(@Valid @ModelAttribute("model") MoodViewModel model,
                                    final BindingResult result, final RedirectAttributes redirectAttributes) {
        model.setProjects(projectService.getProjectsOfCurrentUser());
        model.setBlockedDatesInProjects(projectService.getBlockedDatesInProjectsJsonStream());

        if (model.getDateAdd() != null) {
            if (!moodService.isInLast7Days(model.getDateAdd())) {
                result.rejectValue("dateAdd", "error.invalidDate", "Date must not be earlier than 7 days.");
            }
        }

        Project project = projectDao.findById(model.getProjectId());
        if (project == null) {
            result.rejectValue("projectId", "error.wrongProjectId", "Choose project.");
        } else {
            if (!(project.getOwner().equals(currentUser.getUser()) || project.getJoinedUsersList().contains(currentUser.getUser())))
                result.reject("error.notProjectMember", "You are not a project member.");
            if (moodDao.checkDayMood(currentUser.getUser(), project, model.getDateAdd()))
                result.reject("error.moodAlreadyAdded", "You have already added mood that day.");
        }

        if (result.hasErrors()) {
            model.setMoodType(null);
            return new ModelAndView("/authorised/moodadd", "model", model);
        }

        model.setProject(project);
        moodService.addMood(model);

        // Mood adding successful.
        List<String> infoMessages = new ArrayList<>();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        infoMessages.add("Added mood '" + model.getMoodType() + "' for project '" + model.getProject().getTitle() + "' for " + simpleDateFormat.format(model.getDateAdd()) + " successfully!");

        redirectAttributes.addFlashAttribute("infos", infoMessages);

        return new ModelAndView("redirect:/");
    }

    @RequestMapping(value = "/mood/overview", method = RequestMethod.GET)
    public ModelAndView moodOverview() {
        return new ModelAndView("/authorised/moodoverview", "model", moodService.getMoodsViewModel());
    }
}
