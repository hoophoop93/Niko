package com.pgs.intern.dao;

import com.pgs.intern.models.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Created by kmichalik on 7/26/2016 10:02 AM.
 */
public interface UserRepository extends CrudRepository<User,Long> {

    public User findByEmail(String email);

    public User findByIdUser(Long idUser);

    @Query("SELECT count(o.email) > 0 FROM User o where LOWER(o.email) = :email")
    public boolean checkByEmail(@Param("email")String email);

    @Query("SELECT count(u) > 0 FROM User u, Project p WHERE u.idUser = :idUser AND p.projectId= :projectId AND u in elements ( p.joinedUsersList )")
    public boolean searchUserInProject(@Param("idUser")Long idUser,@Param("projectId") Long projectId);

}
