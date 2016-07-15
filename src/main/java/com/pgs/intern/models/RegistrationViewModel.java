package com.pgs.intern.models;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.Size;

/**
 * Created by lschiffer on 7/12/2016.
 */
public class RegistrationViewModel {

    @NotEmpty(message = "{NotEmpty.message}")
    @Size(max = 32, message = "{Size.displayName}")
    private String displayName;

    @NotEmpty(message = "{NotEmpty.message}")
    @Email(message = "{Email.invalidEmail}")
    private String email;

    @Size(min = 8, message = "{Size.password}")
    private String password;

    @NotEmpty(message = "{NotEmpty.repeatedPassword}")
    private String passwordRepeat;

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

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

    public String getPasswordRepeat() {
        return passwordRepeat;
    }

    public void setPasswordRepeat(String passwordRepeat) {
        this.passwordRepeat = passwordRepeat;
    }
}
