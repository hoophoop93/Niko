package com.pgs.intern.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by Maciej Rosa on 7/12/2016 12:42 PM.
 */
@Controller
public class RegistrationController {

    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public String register(){
        return "unauthorised/register";
    }
}
