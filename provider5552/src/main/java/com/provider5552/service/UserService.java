package com.provider5552.service;

import com.provider5552.entity.User;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.io.Serializable;

@Qualifier("secondaryJdbcTemplate")
public interface UserService extends JpaRepository<User, Long>, Serializable {

    @Query("SELECT  u.password FROM  user u  WHERE u.username= ?1 and u.id = ?2")
    String findOneUser(String name, long id);
}
