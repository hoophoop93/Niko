package com.pgs.intern.controllers;

import com.pgs.intern.services.CurrentUser;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.inject.Inject;

/**
 * Created by Maciej Rosa on 7/13/2016 3:26 PM.
 */
@Controller
public class ProjectController {

    @Inject
    CurrentUser currentUser;

    @RequestMapping(value = "/project/add", method = RequestMethod.GET)
    public String addProject(){
      //  if(currentUser.getUser() == null)
       //     ;//TODO: Anonymous user

        return "authorised/projectadd";
    }
}
