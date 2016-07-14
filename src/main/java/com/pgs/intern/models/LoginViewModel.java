package com.pgs.intern.models;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.Size;

/**
 * Created by kmichalik on 7/12/2016.
 */
public class LoginViewModel {
    @NotEmpty(message = "E-mail is empty.")
    @Email(message = "E-mail is invalid.")
    public String email;

    @NotEmpty(message = "Password is empty.")
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
