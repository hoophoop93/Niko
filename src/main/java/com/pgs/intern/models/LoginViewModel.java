package com.pgs.intern.models;

import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.Size;

/**
 * Created by kmichalik on 7/12/2016.
 */
public class LoginViewModel {

    @NotEmpty
    @Size(max = 32)
    public String displayName;

    @NotEmpty
    public String password;

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
