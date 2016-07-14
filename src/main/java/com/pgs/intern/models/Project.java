package com.pgs.intern.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import javax.validation.constraints.Size;
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

    @OneToMany(mappedBy ="projectId",fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<Mood> project;

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
