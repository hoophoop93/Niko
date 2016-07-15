package com.pgs.intern.controllers;

import com.pgs.intern.dao.UserDao;
import com.pgs.intern.models.LoginViewModel;
import com.pgs.intern.models.User;
import com.pgs.intern.services.CurrentUser;
import com.pgs.intern.utils.AccountUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.ArrayList;

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
        if(currentUser.isAuthenticated())
            return new ModelAndView("redirect:/");
        if ((infos != null) && (!bindingResult.hasErrors())) {
            modelAndView.addObject("infos", infos);
        }

        modelAndView.addObject("model", new LoginViewModel());

        return modelAndView;
    }

    @ResponseBody
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ModelAndView loginPost(@Valid @ModelAttribute("model") LoginViewModel model, final BindingResult result) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("model", model);
        if(currentUser.isAuthenticated())
            return new ModelAndView("redirect:/");
        // Logging in;
        User user = userDao.findUser(model.getEmail());


        if (!result.hasErrors()) {
            if (user == null) {
                result.reject("error.loginError", "Incorrect e-mail or password.");
            } else if (!AccountUtils.validatePassword(model.getPassword(), user.getPasswordHash())) {
                result.reject("error.loginError", "Incorrect e-mail or password.");
            }
        }

        if (result.hasErrors()) {
            modelAndView.setViewName("unauthorised/login");
            return modelAndView;
        }

        currentUser.setUser(user);

        modelAndView.setViewName("redirect:/");
        return modelAndView;
    }

    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String logout(HttpSession session) {
        currentUser.logOut();
        session.invalidate();

        return "redirect:/";
    }
}
