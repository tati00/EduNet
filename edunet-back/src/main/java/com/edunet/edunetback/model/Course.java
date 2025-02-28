package com.edunet.edunetback.model;

import javax.persistence.*;

import java.util.List;

@Entity
@Table(name = "course")
public class Course {
    @Id
    @Column(name = "idcourse")
    private Integer idCourse;
    @Column(name = "asignatura")
    private String asignatura;
    @Column(name = "duracion")
    private Integer duracion;
    @Column(name = "valoracion")
    private String valoracion;

    public Integer getIdCourse() {
        return idCourse;
    }
    public void setIdCourse(Integer idCourse) {
        this.idCourse = idCourse;
    }
    public String getAsignatura() {
        return asignatura;
    }
    public void setAsignatura(String asignatura) {
        this.asignatura = asignatura;
    }
    public Integer getDuracion() {
        return duracion;
    }
    public void setDuracion(Integer duracion) {
        this.duracion = duracion;
    }
    public String getValoracion() {
        return valoracion;
    }
    public void setValoracion(String valoracion) {
        this.valoracion = valoracion;
    }

    @ManyToMany
    @JoinTable(
        name = "course_teacher",
        joinColumns = @JoinColumn(name = "idcourse"),
        inverseJoinColumns = @JoinColumn(name = "idteacher")
    )
    private List<Teacher> teacher;

    public List<Teacher> getTeacher() {
        return teacher;
    }
    public void setTeacher(List<Teacher> teacher) {
        this.teacher = teacher;
    }
    
    @ManyToMany
    @JoinTable(
        name = "course_student",
        joinColumns = @JoinColumn(name = "idcourse"),
        inverseJoinColumns = @JoinColumn(name = "idstudent")
    )
    private List<Student> students;

    public List<Student> getStudents() {
        return students;
    }
    public void setStudents(List<Student> students) {
        this.students = students;
    }
    @ManyToMany
    @JoinTable(
        name = "course_content",
        joinColumns = @JoinColumn(name = "idcourse"),
        inverseJoinColumns = @JoinColumn(name = "idcontent")
    )
    private List<Content> content;

    public List<Content> getContent() {
        return content;
    }
    public void setContent(List<Content> content) {
        this.content = content;
    }
}