package com.edunet.edunetback.repositories;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.edunet.edunetback.model.Student;

public interface StudentRepository extends JpaRepository<Student, String>{
    
    @Query("SELECT s FROM Student s WHERE s.username = :username AND s.password = :password")
    List<Student> findByUserPassword(@Param("username") String username, @Param("password") String password);
}
    
