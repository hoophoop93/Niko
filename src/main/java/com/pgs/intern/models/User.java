package com.pgs.intern.models;

import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.List;
import java.util.Set;

/**
 * Created by kmichalik on 7/12/2016.
 */
@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private long idUser;

    @NotEmpty
    @Size(max = 32)
    @Column(name = "display_name")
    private String displayName;

    @NotEmpty
    @Column(name = "email")
    private String email;

    @NotEmpty
    @Column(name = "password_hash")
    private String passwordHash;

    @OneToMany(mappedBy = "owner", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Project> ownedProjects;

    @OneToMany(mappedBy ="user",fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Mood> moodList;

    @ManyToMany(cascade=CascadeType.ALL,mappedBy = "joinedUsersList")
    private Set<Project> joinedProjectsList;

    public Set<Project> getJoinedProjectsList() {
        return joinedProjectsList;
    }

    public void setJoinedProjectsList(Set<Project> joinedProjectsList) {
        this.joinedProjectsList = joinedProjectsList;
    }

    public long getIdUser() {
        return idUser;
    }

    public void setIdUser(long id) {
        this.idUser = id;
    }

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

    public String getPasswordHash() {
        return passwordHash;
    }

    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }

    public List<Project> getOwnedProjects() {
        return ownedProjects;
    }

    public void setOwnedProjects(List<Project> ownedProjects) {
        this.ownedProjects = ownedProjects;
    }

    public List<Mood> getMoodList() {
        return moodList;
    }

    public void setMoodList(List<Mood> moodList) {
        this.moodList = moodList;
    }

    @Override
    public String toString() {
        return "Id_user: " + idUser +"<br />"+ "DisplayName: " + displayName +"<br />"+ "E-mail: " + email;
    }


}
