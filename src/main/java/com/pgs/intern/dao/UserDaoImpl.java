package com.pgs.intern.dao;

import com.pgs.intern.models.User;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

/**
 * Created by kmichalik on 7/12/2016.
 */
@Repository("UserDaoImpl")
@Transactional(propagation = Propagation.REQUIRED)
public class UserDaoImpl implements UserDao {

    @PersistenceContext
    private EntityManager entityManager;

    public EntityManager getEntityManager() {
        return entityManager;
    }

    public void setEntityManager() {
        this.entityManager = entityManager;
    }

    public void save(User user) {
        entityManager.persist(user);
    }

    public boolean checkByEmail(String email) {
//        String queryString = "SELECT count(o.email) FROM User o where o.email = :email";
        String queryString = "SELECT count(o.email) FROM User o where o.email = :email";
        Query query = getEntityManager().createQuery(queryString);
        query.setParameter("email", email);

        List result = query.getResultList();
        long firstElement = (long) result.get(0);

        if (firstElement > 0) {
            return true;
        } else {
            return false;
        }

    }

    public User findUser(String email) {
        User result = null;
        String queryString = "Select o from User o where o.email = :email ";
        System.out.println(queryString);
        Query query = getEntityManager().createQuery(queryString);
        query.setParameter("email", email);
        try {
            result = (User) query.getSingleResult();
        } catch (Exception e) {
            return null;
        }


        return result;
    }

}
