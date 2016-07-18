package com.pgs.intern.dao;

import com.pgs.intern.models.Project;
import com.pgs.intern.models.User;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

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

    public Project findById(Long id) {
        return entityManager.find(Project.class, id);
    }

    public boolean checkProjectName(String title) {
        String queryString = "SELECT count(o.title) FROM Project o where (o.title) = :title";
        Query query = entityManager.createQuery(queryString);
        query.setParameter("title", title);

        return (long) query.getSingleResult() > 0;

    }

}
