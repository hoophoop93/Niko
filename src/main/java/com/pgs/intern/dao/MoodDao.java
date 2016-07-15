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

    public EntityManager getEntityManager() {
        return entityManager;
    }

    public void setEntityManager() {
        this.entityManager = entityManager;
    }

    public void save(Mood mood) {
        entityManager.persist(mood);
    }

    public boolean checkDayMood(Date dateAdd, User user, Project project) {
        String queryString = "SELECT count(o.dateAdd) FROM Mood o where (u.idUser) =:idUser and (p.projectId)=:projectId and (o.dateAdd) = :dateAdd";
        Query query = getEntityManager().createQuery(queryString);
        query.setParameter("dateAdd", dateAdd);
        query.setParameter("user", user.getIdUser());
        query.setParameter("project", project.getProjectId());

        List result = query.getResultList();
        long firstElement = (long) result.get(0);
        System.out.println(firstElement);

        return (firstElement > 0);
    }
}
