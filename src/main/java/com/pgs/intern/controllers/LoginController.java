package com.pgs.intern.controllers;

import com.pgs.intern.dao.UserDao;
import com.pgs.intern.models.LoginViewModel;
import com.pgs.intern.models.RegistrationViewModel;
import com.pgs.intern.models.User;
import org.apache.commons.validator.routines.EmailValidator;
import org.springframework.beans.factory.annotation.Autowired;
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
 * Created by kmichalik on 7/12/2016.
 */
@Controller
public class LoginController {

    @Autowired
    private UserDao userDao;;

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public ModelAndView login() {
        return new ModelAndView("unauthorised/login", "model", new LoginViewModel());
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


        modelAndView.addObject("errors", errorMessages);
        if (!errorMessages.isEmpty()) {
            modelAndView.setViewName("unauthorised/login");
            return modelAndView;
        }
        if (result.hasErrors()) {
            modelAndView.setViewName("unauthorised/login");
            return modelAndView;
        }


        User user = userDao.findUser(model.getEmail());

        System.out.println(user);

        if(user == null){
            System.out.println("bład");
        }


            modelAndView.setViewName("authorised/index");
            return modelAndView;


    }
}
