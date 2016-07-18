package com.pgs.intern.models;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.Size;

/**
 * Created by kmichalik on 7/12/2016.
 */
public class LoginViewModel {
    @NotEmpty(message = "{NotEmpty.message}")
    @Email(message = "{Email.invalidEmail}")
    public String email;

    @NotEmpty(message = "{NotEmpty.message}")
    public String password;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email.trim();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
