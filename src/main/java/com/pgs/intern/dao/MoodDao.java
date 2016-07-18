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
import java.util.Date;
import java.util.List;

/**
 * Created by mzalucka on 7/14/2016.
 */
@Repository
@Transactional(propagation = Propagation.REQUIRED)
public class MoodDao {

    @PersistenceContext
    private EntityManager entityManager;

    public void save(Mood mood) {
        entityManager.persist(mood);
    }

    public boolean checkDayMood(Date dateAdd, User user, Project project) {
        String queryString = "SELECT count(o.dateAdd) FROM Mood o WHERE (o.user) =:user AND (o.project)=:project AND (o.dateAdd) = :dateAdd";
        TypedQuery<Long> query = entityManager.createQuery(queryString,Long.class);
        query.setParameter("dateAdd", dateAdd);
        query.setParameter("user", user);
        query.setParameter("project", project);

        return query.getSingleResult() > 0;

    }
}
