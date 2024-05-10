package com.example.stud_assignment3.entity;


import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

import java.util.Set;

@Entity
@Table(name="ASSIGNMENT")
public class Assignment {

    @Id
    @GeneratedValue
    private Long id;
    private String title;


    private double score;


    //@JsonIgnore
    @ManyToMany(mappedBy = "assignments", fetch=FetchType.LAZY)
    @JsonBackReference
    private Set<Student> students;

    @ManyToOne(cascade=CascadeType.ALL)
    @JoinColumn(name = "professor_id")
    private Professor professor;

    @ManyToOne(cascade=CascadeType.ALL)
    @JoinColumn(name = "subject_id")
    private Subject subject;

    @OneToOne
    @JoinColumn(name="grade_id")
    private Grades grade;
    public Assignment(Professor professor,Subject subject,double score) {
        this.professor = professor;
        this.subject=subject;
        this.score=score;
    }


    public Assignment() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
    }

    public Set<Student> getStudents() {
        return students;
    }

    public void setStudents(Set<Student> students) {
        this.students = students;
    }

    public Professor getProfessor() {
        return professor;
    }

    public void setProfessor(Professor professor) {
        this.professor = professor;
    }

    public Subject getSubject() {
        return subject;
    }

    public void setSubject(Subject subject) {
        this.subject = subject;
    }

    public Grades getGrade() {
        return grade;
    }

    public void setGrade(Grades grade) {
        this.grade = grade;
    }


}

