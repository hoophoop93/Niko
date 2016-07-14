package com.pgs.intern.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by Maciej Rosa on 7/14/2016 1:48 PM.
 */

@Controller
public class MoodController {

    @RequestMapping(value = "/mood/add", method = RequestMethod.GET)
    public ModelAndView addMood(){
        return new ModelAndView("authorised/moodadd"/* TODO: ,new MoodViewModel()*/);
    }

}
