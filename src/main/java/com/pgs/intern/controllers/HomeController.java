package com.pgs.intern.controllers;

import com.pgs.intern.utils.AccountUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by lschiffer on 7/12/2016.
 */
@Controller
public class HomeController {
    @RequestMapping("/")
    public String index() {
        return "unauthorised/index";
    }

    // For testing only; TODO REMOVE ME LATER
    @RequestMapping("/niko")
    public String niko() {
        return "authorised/index";
    }

    // For testing only; TODO REMOVE ME LATER
    @ResponseBody
    @RequestMapping("/test")
    public String test() {
        // Register
        String test = "http://localhost:8080/register";
        String hash = AccountUtils.getHashFor(test);

        // From login form and db
        String testIn = "http://localhost:8080/register";
        String hashIn = hash;

        return test + " -> " + hash + "<br/>" + testIn + " -> " + AccountUtils.validatePassword(testIn, hashIn);
    }
}
