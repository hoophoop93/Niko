package com.pgs.intern.controllers;

import com.pgs.intern.dao.MoodDao;
import com.pgs.intern.dao.ProjectDao;
import com.pgs.intern.models.MoodViewModel;
import com.pgs.intern.models.Project;
import com.pgs.intern.services.CurrentUser;
import com.pgs.intern.services.MoodAdder;
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
    private MoodAdder moodAdder;

    @Autowired
    private MoodDao moodDao;


    @RequestMapping(value = "/mood/add", method = RequestMethod.GET)
    public ModelAndView addMood() {
        MoodViewModel mood = new MoodViewModel();
        mood.setDateAdd(new Date());

        ModelAndView modelAndView = new ModelAndView("authorised/moodadd", "model", mood);

        if (!currentUser.isAuthenticated()) {
            return new ModelAndView("redirect:/login");
        }

        return modelAndView;
    }

    @RequestMapping(value = "/mood/add", method = RequestMethod.POST)
    public ModelAndView addMoodPost(@Valid @ModelAttribute("model") MoodViewModel model,
                                    final BindingResult result, final RedirectAttributes redirectAttributes) {
        if (!currentUser.isAuthenticated()) {
            return new ModelAndView("redirect:/login");
        }

        Project project = projectDao.findById(model.getProjectId());
        if(project == null) {
            result.rejectValue("projectId", "error.wrongProjectId", "Choose project.");
        }else{
            if(!project.getOwner().equals(currentUser.getUser()))
                result.reject("error.projectNotOwned", "You are not a project owner.");
            if(moodDao.checkDayMood(model.getDateAdd(), currentUser.getUser(),project))
                result.reject("error.moodAlreadyAdded","You have already added mood that day.");
        }


        if (result.hasErrors()) {
            return new ModelAndView("authorised/moodadd", "model", model);
        }

        model.setProject(project);
        moodAdder.addMood(model);

        // Mood adding successful.
        List<String> infoMessages = new ArrayList<>();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        infoMessages.add("Added mood '" + model.getMoodType() + "' for project '" + model.getProject().getTitle() + "' for " + simpleDateFormat.format(model.getDateAdd()) + " successfully!");

        redirectAttributes.addFlashAttribute("infos", infoMessages);

        return new ModelAndView("redirect:/");
    }

}
