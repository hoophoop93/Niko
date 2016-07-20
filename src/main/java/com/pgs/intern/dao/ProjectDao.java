package com.pgs.intern.dao;

import com.pgs.intern.models.Mood;
import com.pgs.intern.models.Project;
import com.pgs.intern.models.User;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.List;

/**
 * Created by mzalucka on 7/13/2016.
 */

@Repository
@Transactional(propagation = Propagation.REQUIRED)
public class ProjectDao {

    @PersistenceContext
    private EntityManager entityManager;

    public void save(Project project) {
        entityManager.persist(project);
    }

    public void update(Project project) {
        entityManager.merge(project);
    }

    public Project findById(Long id) {
        return entityManager.find(Project.class, id);
    }

    public boolean checkProjectTitle(String title) {
        String queryString = "SELECT count(o.title) FROM Project o where LOWER(o.title) = :title";
        TypedQuery<Long> query = entityManager.createQuery(queryString, Long.class);
        query.setParameter("title", title.toLowerCase());

        return query.getSingleResult() > 0;

    }

    public List<Project> getSortedOwnedProjects(User owner) {
        String queryString = "SELECT o FROM Project o WHERE o.owner = :owner ORDER BY o.title ASC";
        TypedQuery<Project> query = entityManager.createQuery(queryString, Project.class);
        query.setParameter("owner", owner);
        System.out.println(query.getResultList());

        return query.getResultList();
    }

    public List<Mood> getMoodList(User user, Project project) {
        String queryString = "SELECT o FROM Mood o WHERE o.user = :user AND o.project = :project";
        TypedQuery<Mood> query = entityManager.createQuery(queryString, Mood.class);
        query.setParameter("user", user);
        query.setParameter("project", project);

        return query.getResultList();

    }

    public List<User> getNoneJoinedUsers(Project project) {
        String queryString = "SELECT u FROM User u, Project p WHERE p = :project AND u not in elements( p.joinedUsers ) and u <> p.owner";
        TypedQuery<User> query = entityManager.createQuery(queryString, User.class);
        query.setParameter("project", project);

        return query.getResultList();
    }

    public List<User> getNoneJoinedUsersById(Long projectId) {
        String queryString = "SELECT u FROM User u, Project p WHERE p.projectId = :projectId AND u not in elements( p.joinedUsers ) and u <> p.owner";
        TypedQuery<User> query = entityManager.createQuery(queryString, User.class);
        query.setParameter("projectId", projectId);

        return query.getResultList();
    }
}
