package com.pgs.intern.controllers;

import com.pgs.intern.models.LoginViewModel;
import com.pgs.intern.models.RegistrationViewModel;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

/**
 * Created by kmichalik on 7/12/2016.
 */
@Controller
public class LoginController {

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public ModelAndView register() {
        return new ModelAndView("unauthorised/login", "model", new LoginViewModel());
    }

    @ResponseBody
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public LoginViewModel loginPost(@Valid @ModelAttribute LoginViewModel model, final BindingResult result) {
        if (result.hasErrors()) {
            return null;
        }

        return model;
    }
}
