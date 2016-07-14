package com.pgs.intern.models;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.Size;

/**
 * Created by lschiffer on 7/12/2016.
 */
public class RegistrationViewModel {

    @NotEmpty(message = "Display name is empty.")
    @Size(max = 32, message = "Display name is too long. You can use maximum 32 characters.")
    private String displayName;

    @NotEmpty(message = "E-mail is empty")
    @Email(message = "E-mail is invalid.")
    private String email;

    @NotEmpty
    @Size(min = 8, message = "Password is too short. You should use minimum 8 characters")
    private String password;

    @NotEmpty(message = "Reapeated password is empty")
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
