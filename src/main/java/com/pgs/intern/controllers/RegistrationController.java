package com.pgs.intern.controllers;

import com.pgs.intern.dao.UserDao;
import com.pgs.intern.models.RegistrationViewModel;
import com.pgs.intern.services.CurrentUser;
import com.pgs.intern.services.RegistrationService;
import org.apache.commons.validator.routines.EmailValidator;
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
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Maciej Rosa on 7/12/2016 12:42 PM.
 */
@Controller
public class RegistrationController {

    @Autowired
    private UserDao userDao;

    @Inject
    private CurrentUser currentUser;

    @Autowired
    private RegistrationService registrationService;

    private final EmailValidator emailValidator = EmailValidator.getInstance();


    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public ModelAndView register() {
        if (currentUser.isAuthenticated())
            return new ModelAndView("redirect:/");

        return new ModelAndView("unauthorised/register", "model", new RegistrationViewModel());
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public ModelAndView registerPost(@Valid @ModelAttribute("model") RegistrationViewModel model, final BindingResult result, final RedirectAttributes redirectAttributes) {
        if(currentUser.isAuthenticated()) {
            return new ModelAndView("redirect:/");
        }

        if(userDao.checkByEmail(model.getEmail())) {
            result.reject("error.registrationError","E-mail already taken.");
        }

        if (result.hasErrors()) {
            return new ModelAndView("unauthorised/register","model",model);
        }

        registrationService.registration(model);

        List<String> infoMessages = new ArrayList<>();
        infoMessages.add("Registration was successful! Now, you can login.");

        redirectAttributes.addFlashAttribute("infos", infoMessages);

        return new ModelAndView("redirect:/login");
    }
}
