package com.pgs.intern.models;

import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import java.util.Date;

/**
 * Created by lschiffer on 7/14/2016.
 */
public class MoodViewModel {
    @NotNull(message = "Choose mood.")
    private MoodType moodType;

    @NotNull(message = "Choose date.")
    @Past(message = "Choose date from today or past.")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date dateAdd;

    private Project project;

    @NotNull(message = "Choose project.")
    @Min(value = 1, message = "Choose project.")
    private long projectId;

    public MoodType getMoodType() {
        return moodType;
    }

    public void setMoodType(MoodType moodType) {
        this.moodType = moodType;
    }

    public Date getDateAdd() {
        return dateAdd;
    }

    public void setDateAdd(Date dateAdd) {
        this.dateAdd = dateAdd;
    }

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    public long getProjectId() {
        return projectId;
    }

    public void setProjectId(long projectId) {
        this.projectId = projectId;
    }
}