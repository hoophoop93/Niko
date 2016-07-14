package com.pgs.intern.controllers;

import com.pgs.intern.models.MoodViewModel;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

/**
 * Created by Maciej Rosa on 7/14/2016 1:48 PM.
 */

@Controller
public class MoodController {

    @RequestMapping(value = "/mood/add", method = RequestMethod.GET)
    public ModelAndView addMood(){
        return new ModelAndView("authorised/moodadd","model",new MoodViewModel());
    }

    @ResponseBody
    public MoodViewModel addMoodPost(@Valid @ModelAttribute("model") MoodViewModel model,
                                     final BindingResult result, final RedirectAttributes redirectAttributes){
        return model;
    }

}
