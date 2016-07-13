package com.pgs.intern.controllers;

import com.pgs.intern.dao.UserDao;
import com.pgs.intern.models.RegistrationViewModel;
import com.pgs.intern.models.User;
import org.apache.commons.validator.routines.EmailValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Maciej Rosa on 7/12/2016 12:42 PM.
 */
@Controller
public class RegistrationController {

    @Autowired
    private UserDao userDao;

    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public ModelAndView register() {
        return new ModelAndView("unauthorised/register", "model", new RegistrationViewModel());
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public ModelAndView registerPost(@Valid @ModelAttribute RegistrationViewModel model, final BindingResult result) {
        ModelAndView modelAndView = new ModelAndView();
        List<String> errorMessages = new ArrayList<>();
        modelAndView.addObject("model", model);
        modelAndView.addObject("errors", errorMessages);


        if (model.getEmail().isEmpty())
            errorMessages.add("E-mail is empty.");
        else if(!EmailValidator.getInstance().isValid(model.getEmail()))
            errorMessages.add("E-mail is invalid.");
        if (model.getDisplayName().isEmpty())
            errorMessages.add("Display name is empty.");
        if(userDao.checkByEmail(model.getEmail()))
            errorMessages.add("E-mail already taken.");
        if (model.getDisplayName().length() > 32)
            errorMessages.add("Display name is too long.");
        if (!model.getPassword().equals(model.getPasswordRepeat()))
            errorMessages.add("Passwords do not match.");
        if (model.getPassword().length() < 8)
            errorMessages.add("Password is too short (minimum 8 characters).");

        modelAndView.addObject("errors", errorMessages);

        if (!errorMessages.isEmpty()) {
            modelAndView.setViewName("unauthorised/register");
            return modelAndView;
        }

        if (result.hasErrors()) {
            modelAndView.setViewName("unauthorised/register");
            return modelAndView;
        }
        // TODO: register

        List<String> infoMessages = new ArrayList<>();
        infoMessages.add("Registration was successful! Now, you can login.");

        modelAndView.addObject("infos", infoMessages);

        modelAndView.setViewName("unauthorised/login");

        return modelAndView;
    }
}
