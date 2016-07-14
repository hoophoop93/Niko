package com.pgs.intern.controllers;

import com.pgs.intern.services.CurrentUser;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.inject.Inject;
import java.util.ArrayList;

/**
 * Created by lschiffer on 7/12/2016.
 */
@Controller
public class HomeController {
    @Inject
    private CurrentUser currentUser;

    @RequestMapping("/")
    public ModelAndView index(@ModelAttribute("infos") final ArrayList<String> infos,
                        final BindingResult bindingResult) {
        ModelAndView modelAndView = new ModelAndView();

        if ((infos != null) && (!bindingResult.hasErrors())) {
            modelAndView.addObject("infos", infos);
        }

        if(currentUser.isAuthenticated()) {
            modelAndView.setViewName("authorised/index");
        } else {
            modelAndView.setViewName("unauthorised/index");
        }

        return modelAndView;
    }
}