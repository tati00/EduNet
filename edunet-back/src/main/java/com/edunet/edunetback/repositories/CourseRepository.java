package com.edunet.edunetback.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.edunet.edunetback.model.Course;

public interface CourseRepository extends JpaRepository<Course, Integer>{
    List<Course> findByContent(Integer idCourse);
    @Query("SELECT s FROM Course s WHERE s.asignatura = :asignatura")
    List<Course> findBySignature(@Param("asignatura") String asignatura);
}
