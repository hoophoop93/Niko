package com.pgs.intern.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.Date;

/**
 * Created by kmichalik on 7/14/2016.
 */
@Entity
@Table(name = "mods")
public class Mood {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "mood_id")
    private long moodId;

    @NotEmpty
    @Column(name = "mood")
    private MoodType moodType;

    @NotEmpty
    @Column(name = "date_add")
    private Date dateAdd;

    @ManyToOne
    @JoinColumn(name = "user_id")
    @JsonIgnore
    private User userId;

    @ManyToOne
    @JoinColumn(name = "project_id")
    @JsonIgnore
    private Project projectId;

    public long getMoodId() {

        return moodId;
    }

    public void setMoodId(long moodId) {
        this.moodId = moodId;
    }

    public Date getDateAdd() {
        return dateAdd;
    }

    public void setDateAdd(Date dateAdd) {
        this.dateAdd = dateAdd;
    }

    public MoodType getMoodType() {
        return moodType;
    }

    public void setMoodType(MoodType moodType) {
        this.moodType = moodType;
    }
}
