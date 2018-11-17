package com.xmlu.sprigboot.jpa.exam.dao;

import com.xmlu.sprigboot.jpa.exam.dao.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UserRepository extends JpaRepository<User, String>, JpaSpecificationExecutor<User> {
    @Query("select t from User t where t.id=:id")
	User getUserById(@Param("id") Integer id);
}
