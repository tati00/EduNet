package com.edunet.edunetback.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.edunet.edunetback.model.Teacher;

public interface TeacherRepository extends JpaRepository<Teacher, String>{
    @Query("SELECT t FROM Teacher t WHERE t.username = :username AND t.password = :password")
    List<Teacher> findByUserPassword(@Param("username") String username, @Param("password") String password);
}
