package com.pgs.intern.dao;

import com.pgs.intern.models.User;

/**
 * Created by kmichalik on 7/12/2016.
 */
public interface UserDao {

    public User findUser(String email);
    public boolean checkByEmail(String email);
    void save(User user);

}
