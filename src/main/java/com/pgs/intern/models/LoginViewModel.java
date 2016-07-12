package com.pgs.intern.models;

import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.Size;

/**
 * Created by kmichalik on 7/12/2016.
 */
public class LoginViewModel {

    @NotEmpty

    public String email;

    @NotEmpty
    public String password;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
