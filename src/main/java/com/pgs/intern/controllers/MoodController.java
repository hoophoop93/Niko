package com.pgs.intern.controllers;

import com.pgs.intern.models.MoodViewModel;
import com.pgs.intern.services.CurrentUser;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.inject.Inject;
import javax.validation.Valid;
import java.util.Date;

/**
 * Created by Maciej Rosa on 7/14/2016 1:48 PM.
 */

@Controller
public class MoodController {

    @Inject
    CurrentUser currentUser;

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

        if (result.hasErrors()) {
            return new ModelAndView("authorised/moodadd", "model", model);
        }

        return new ModelAndView("redirect:/");
    }

}
