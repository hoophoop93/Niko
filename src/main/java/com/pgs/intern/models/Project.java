package com.pgs.intern.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import java.util.List;

/**
 * Created by mzalucka on 7/13/2016.
 */
@Entity
@Table(name = "projects")
public class Project {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "project_id")
    private long projectId;

    @NotEmpty
    @Column(name = "title")
    private String title;

    @ManyToOne
    @JoinColumn(name = "project_owner")
    @JsonIgnore
    private User owner;

    @OneToMany(mappedBy = "project", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Mood> moodList;


    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name="project_user", joinColumns = @JoinColumn(name="joined_project"),
            inverseJoinColumns = @JoinColumn(name="joined_user"))
    private List<User> joinedUsers;

    public List<User> getJoinedUsers() {
        return joinedUsers;
    }

    public void setJoinedUsers(List<User> joinedUsers) {
        this.joinedUsers = joinedUsers;
    }

    public List<Mood> getMoodList() {
        return moodList;
    }
    public void setMoodList(List<Mood> moodList) {
        this.moodList = moodList;
    }


    public long getProjectId() {
        return projectId;
    }

    public void setProjectId(long projectId) {
        this.projectId = projectId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }


    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }
}
