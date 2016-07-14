package com.pgs.intern.services;

import com.pgs.intern.dao.ProjectDao;
import com.pgs.intern.dao.UserDao;
import com.pgs.intern.models.ProjectViewModel;
import com.pgs.intern.models.RegistrationViewModel;
import com.pgs.intern.models.User;
import com.pgs.intern.utils.AccountUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by kmichalik on 7/14/2016.
 */
@Component
public class RegistrationService {

    @Autowired
    private UserDao userDao;


    public void registration(RegistrationViewModel registrationViewModel){
        User user = new User();
        user.setEmail(registrationViewModel.getEmail());
        user.setDisplayName(registrationViewModel.getDisplayName());
        user.setPasswordHash(AccountUtils.getHashFor(registrationViewModel.getPassword()));

        userDao.save(user);
    }







}
