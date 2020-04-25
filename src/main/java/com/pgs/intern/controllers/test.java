package com.pgs.intern.controllers;

import com.pgs.intern.dao.MoodDaoDataJpaInterface;
import com.pgs.intern.dao.ProjectDao;
import com.pgs.intern.dao.UserDao;
import com.pgs.intern.dao.UserRepository;
import com.pgs.intern.models.Project;
import com.pgs.intern.models.User;
import com.pgs.intern.services.CurrentUser;
import com.pgs.intern.services.ProjectService;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.inject.Inject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by kmichalik on 7/19/2016 1:50 PM.
 */
@RestController
public class test{

    @Autowired
    UserRepository userRepository;
    @Autowired
    ProjectDao projectDao;

    @Autowired
    private UserDao userDao;

    @Autowired
    private ProjectService projectService;

    @Autowired
    private MoodDaoDataJpaInterface moodDaoDataJpaInterface;

    @RequestMapping("/test")
    public String testt() {

        StringBuilder stringBuilder = new StringBuilder();
        for (User user : projectDao.getNoneJoinedUsersById(1L))
            stringBuilder.append(user.getDisplayName()).append("\n");


        return stringBuilder.toString();

    }
    @RequestMapping("/test2")
    public boolean test2() {


        return userDao.searchUserInProject(2L,2L);

    }
    @RequestMapping("/testuser")
    public String testUser() {

        return "Check table";

    }
    @RequestMapping("/test3")
    public String test3() {

        StringBuilder stringBuilder = new StringBuilder();
        for (Project project : projectDao.getUserProjects(userDao.findById(5L)))
            stringBuilder.append(project.getTitle()).append("     ----     ");


        return stringBuilder.toString();

    }
    @RequestMapping("/test4")
    public String doSomething() {
        User user = userRepository.findByEmail("jnowak@wp.pl");
        System.out.println(user);

        return "user";

    }
    @RequestMapping("/test6")
    public boolean test20() {

        return userRepository.checkByEmail("jnowafk@wp.pl");
    }
    @RequestMapping("/test7")
    public boolean test21() {

        return userRepository.searchUserInProject(1L,2L);
    }

    @RequestMapping("/test8")
    public String doSomething2() {
        //User user = userRepository.findByIdUser(2L);
        //System.out.println(user);

        StringBuilder stringBuilder = new StringBuilder();

        for (long i = 2 ; i < 8 ; i++) {
            stringBuilder.append(userRepository.findByIdUser(i)).append("<br />");
        }


        return stringBuilder.toString();

    }
    @RequestMapping("/test9")
    public boolean doSomething3() throws ParseException {

        return moodDaoDataJpaInterface.checkDisplayNameUnique("messi");
    }
}