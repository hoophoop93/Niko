package com.pgs.intern.controllers;

import com.pgs.intern.models.RegistrationViewModel;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Maciej Rosa on 7/12/2016 12:42 PM.
 */
@Controller
public class RegistrationController {
    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public ModelAndView register(){
        return new ModelAndView("unauthorised/register","model",new RegistrationViewModel());
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public ModelAndView registerPost(@Valid @ModelAttribute RegistrationViewModel model, final BindingResult result) {
        ModelAndView modelAndView = new ModelAndView();
        List<String> errorMessages = new ArrayList<>();
        modelAndView.addObject("model",model);
        modelAndView.addObject("errors",errorMessages);

        if(model.getDisplayName().length() > 32)
            errorMessages.add("Display name is too long");
        if(!model.getPassword().equals(model.getPasswordRepeat()))
            errorMessages.add("Passwords do not match.");
        if(model.getPassword().length() < 8)
            errorMessages.add("Password is too short (minimum 8 characters).");

        if(!errorMessages.isEmpty()){
            modelAndView.setViewName("unauthorised/register");
            return modelAndView;
        }

        if (result.hasErrors()) {
            modelAndView.setViewName("unauthorised/register");
            return modelAndView;
        }
        // TODO: register

        modelAndView.setViewName("unauthorised/register");

        return modelAndView;
    }
}
