package com.pgs.intern.models;

import de.malkusch.validation.constraints.EqualProperties;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

/**
 * Created by lschiffer on 7/12/2016.
 */
@EqualProperties(value = {"password", "passwordRepeat"}, message = "{Passwords.dontMatch}")
public class RegistrationViewModel {
    @NotBlank(message = "{NotEmpty.message}")
    @Size(max = 32, message = "{Size.displayName}")
    private String displayName;

    @Pattern(regexp = "^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$", message = "{Email.invalidEmail}")
    private String email;

    @Size(min = 8, message = "{Size.password}")
    private String password;

    @NotEmpty(message = "{NotEmpty.repeatedPassword}")
    private String passwordRepeat;

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName.trim();
    }

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

    public String getPasswordRepeat() {
        return passwordRepeat;
    }

    public void setPasswordRepeat(String passwordRepeat) {
        this.passwordRepeat = passwordRepeat;
    }
}
