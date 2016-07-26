package com.pgs.intern.dao;

import com.pgs.intern.models.Project;
import com.pgs.intern.models.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Created by Maciej Rosa on 7/26/2016 10:30 AM.
 */

public interface ProjectDaoJpa extends CrudRepository<Project,Long> {

    Project findByTitle(String title);

    List<Project> findByOwnerOrderByTitleAsc(User owner);

    @Query("SELECT count(o.title) > 0 FROM Project o where LOWER(o.title) =  LOWER(:title)")
    boolean existsByTitle(@Param("title") String title);

    @Query("SELECT p FROM Project p, User u WHERE u = :user AND (u = p.owner OR p in elements(u.joinedProjectsList)) ORDER BY p.title ASC")
    List<Project> getUserProjects(@Param("user") User user);

    @Query("SELECT u FROM User u, Project p WHERE p.projectId = :projectId AND u not in elements( p.joinedUsersList ) and u <> p.owner")
    List<User> getNoneJoinedUsersById(@Param("projectId") Long projectId);
}
