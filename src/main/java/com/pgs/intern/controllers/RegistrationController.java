package com.pgs.intern.controllers;

import com.pgs.intern.models.RegistrationViewModel;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.Valid;

/**
 * Created by Maciej Rosa on 7/12/2016 12:42 PM.
 */
@Controller
public class RegistrationController {
    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public String register(){
        return "unauthorised/register";
    }

    @ResponseBody
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public RegistrationViewModel registerPost(@Valid @ModelAttribute RegistrationViewModel model, final BindingResult result) {
        if (result.hasErrors()) {
            return null;
        }

        return model;
    }
}
