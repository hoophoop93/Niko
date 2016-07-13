package com.pgs.intern.models;

import org.hibernate.validator.constraints.NotEmpty;

/**
 * Created by lschiffer on 7/13/2016.
 */
public class ProjectViewModel {
    @NotEmpty
    public String title;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
