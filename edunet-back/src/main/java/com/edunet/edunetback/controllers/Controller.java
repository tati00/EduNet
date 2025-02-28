package com.edunet.edunetback.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.edunet.edunetback.model.*;
import com.edunet.edunetback.repositories.*;

@RestController
@CrossOrigin(origins = "http://127.0.0.1:3000")
public class Controller {
    @Autowired
    private StudentRepository studentRepository;
    @Autowired
    private TeacherRepository teacherRepository;
    @Autowired
    private CourseRepository courseRepository;
    @Autowired
    private ContentRepository contentRepository;

    @GetMapping("/students")
    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    @GetMapping("/teacher")
    public List<Teacher> getAllTeacher() {
        return teacherRepository.findAll();
    }

    @GetMapping("/course")
    public List<Course> getAllCourses() {
        return courseRepository.findAll();
    }

    @GetMapping("/{idCourse}/course_teacher")
    public List<Teacher> getTeacherByCourse(@PathVariable int idCourse) {
        Course course = courseRepository.findById(idCourse).orElse(null);
        if (course != null) {
            return course.getTeacher();
        }
        return null;
    }

    @GetMapping("/{username}/{password}/student")
    public List<Student> getStudentUserPassword(@PathVariable String username, @PathVariable String password) {
            return studentRepository.findByUserPassword(username, password);
    }

    @GetMapping("/{username}/{password}/teacher")
    public List<Teacher> getTeacherUserPassword(@PathVariable String username, @PathVariable String password) {
            return teacherRepository.findByUserPassword(username, password);
    }

    @GetMapping("/{idCourse}/course_student")
    public List<Student> getStudentsByCourse(@PathVariable int idCourse) {
        Course course = courseRepository.findById(idCourse).orElse(null);
        if (course != null) {
            return course.getStudents();
        }
        return null;
    }

    
    @PostMapping("/students")
    public ResponseEntity<Student> signInStudent(@RequestBody Student student) {
        try {
            if (studentRepository.existsById(student.getUsername())) {
                return ResponseEntity.status(HttpStatus.CONFLICT).build();
            }
            studentRepository.save(student);
            return ResponseEntity.ok(student);
        } catch (Exception e) {
            e.printStackTrace(); 
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PostMapping("/teacher")
    public ResponseEntity<Teacher> signInTeacher(@RequestBody Teacher teacher) {
        try {
            if (teacherRepository.existsById(teacher.getUsername())) {
                return ResponseEntity.status(HttpStatus.CONFLICT).build();
            }
            teacherRepository.save(teacher);
            return ResponseEntity.ok(teacher);
        } catch (Exception e) {
            e.printStackTrace(); 
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/{idCourse}/course_content")
    public ResponseEntity<Course> getContentByCourse(@PathVariable String idCourse) {
        Optional<Course> courses = courseRepository.findById(Integer.parseInt(idCourse));
        if (courses.isPresent()) {
            return ResponseEntity.ok(courses.get());
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @GetMapping("/content")
    public List<Content> getAllContents() {
        return contentRepository.findAll();
    }

    @PostMapping("/content")
    public ResponseEntity<Content> createContent(@RequestBody Content content) {
        contentRepository.save(content);
        return ResponseEntity.ok(content);
    }

    @PatchMapping("/content")
    public ResponseEntity<Content> updateContent(@RequestBody Content content) {
        contentRepository.save(content);
        return ResponseEntity.ok(content);
    }

    @PostMapping("/course")
    public ResponseEntity<Course> createCourse(@RequestBody Course course) {
        courseRepository.save(course);
        return ResponseEntity.ok(course);
    }

    @DeleteMapping("/{idCourse}/course")
    public ResponseEntity<Course> deleteCourse(@PathVariable Integer idCourse) {
        courseRepository.deleteById(idCourse);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @GetMapping("/{asignatura}/course")
    public List<Course> getCourse(@PathVariable String asignatura) {
            return courseRepository.findBySignature(asignatura);
    }
}