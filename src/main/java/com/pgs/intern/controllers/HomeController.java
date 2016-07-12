package com.pgs.intern.controllers;

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
}
