package com.pgs.intern.controllers;

import com.pgs.intern.dao.UserDao;
import com.pgs.intern.models.LoginViewModel;
import com.pgs.intern.models.User;
import com.pgs.intern.services.CurrentUser;
import com.pgs.intern.utils.AccountUtils;
import org.apache.commons.validator.routines.EmailValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.inject.Inject;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by kmichalik on 7/12/2016.
 */
@Controller
public class LoginController {

    @Autowired
    private UserDao userDao;

    @Inject
    private CurrentUser currentUser;

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public ModelAndView login(@ModelAttribute("infos") final ArrayList<String> infos,
                              final BindingResult bindingResult) {
        ModelAndView modelAndView = new ModelAndView("unauthorised/login");

        if ((infos != null) && (!bindingResult.hasErrors())) {
            modelAndView.addObject("infos", infos);
        }

        modelAndView.addObject("model", new LoginViewModel());

        return modelAndView;
    }

    @ResponseBody
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ModelAndView loginPost(@Valid @ModelAttribute LoginViewModel model, final BindingResult result) {
        ModelAndView modelAndView = new ModelAndView();
        List<String> errorMessages = new ArrayList<>();
        modelAndView.addObject("model", model);
        modelAndView.addObject("errors", errorMessages);

        if (model.getEmail().isEmpty()) {
            errorMessages.add("E-mail is empty.");
        } else if (!EmailValidator.getInstance().isValid(model.getEmail()))
            errorMessages.add("E-mail is invalid.");
        if (model.getPassword().isEmpty()) {
            errorMessages.add("Password is empty.");
        }

        // Logging in;
        if (errorMessages.isEmpty()) {
            User user = userDao.findUser(model.getEmail());

            if (user != null) {
                if (AccountUtils.validatePassword(model.getPassword(), user.getPasswordHash())) {
                    // Valid login details;

                    currentUser.setUser(user);

                } else {
                    errorMessages.add("Incorrect e-mail or password.");
                }
            } else {
                errorMessages.add("Incorrect e-mail or password.");
            }
        }

        modelAndView.addObject("errors", errorMessages);
        if (!errorMessages.isEmpty()) {
            modelAndView.setViewName("unauthorised/login");
            return modelAndView;
        }
        if (result.hasErrors()) {
            modelAndView.setViewName("unauthorised/login");
            return modelAndView;
        }

        modelAndView.setViewName("redirect:/");
        return modelAndView;
    }
}
