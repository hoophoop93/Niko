package com.pgs.intern.services;

import com.pgs.intern.dao.UserDao;
import com.pgs.intern.dao.UserRepository;
import com.pgs.intern.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

/**
 * Created by Maciej Rosa on 7/13/2016 1:26 PM.
 */
@Component
@Scope(value = "session", proxyMode = ScopedProxyMode.TARGET_CLASS)
public class CurrentUser {
    private User user;

    @Autowired
    UserRepository userRepository;

    public User getUser() {
        if(user != null) {
            user = userRepository.findByIdUser(user.getIdUser());
        }

        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public boolean isAuthenticated() {
        return (user != null);
    }

    public void logOut() {
        user = null;
    }
}
