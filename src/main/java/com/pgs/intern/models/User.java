package com.pgs.intern.models;

import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.List;

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

    @OneToMany(mappedBy ="user",fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<Mood> userMoods;

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

    public List<Mood> getUserMoods() {
        return userMoods;
    }

    public void setUserMoods(List<Mood> userMoods) {
        this.userMoods = userMoods;
    }

    @Override
    public String toString() {
        return "Id_user: " + idUser + "DispayName: " + displayName + "E-mail: " + email;
    }


}
