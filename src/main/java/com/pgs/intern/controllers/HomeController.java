package com.pgs.intern.controllers;

import com.pgs.intern.services.CurrentUser;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.inject.Inject;

/**
 * Created by lschiffer on 7/12/2016.
 */
@Controller
public class HomeController {
    @Inject
    private CurrentUser currentUser;

    @RequestMapping("/")
    public String index() {
        if(currentUser.isAuthenticated()) {
            return "authorised/index";
        } else {
            return "unauthorised/index";
        }
    }
}
