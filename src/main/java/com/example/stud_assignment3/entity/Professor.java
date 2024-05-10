package com.example.stud_assignment3.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name="PROFFESOR")
public class Professor {
    @Id
    @GeneratedValue
    private Long id;

    private String name;

    @OneToMany(cascade = CascadeType.ALL,mappedBy = "professor")
    private List<Exam> exams;

    @OneToMany(cascade = CascadeType.ALL,mappedBy = "professor")
    private List<Exercise> exercises;

    @OneToMany(cascade = CascadeType.ALL,mappedBy = "professor")
    private List<Assignment> assignments;


    @OneToMany(cascade = CascadeType.ALL,mappedBy ="professor" )
    private List<Subject> subjects;

    public Professor(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Exam> getExams() {
        return exams;
    }

    public void setExams(List<Exam> exams) {
        this.exams = exams;
    }

    public List<Exercise> getExercises() {
        return exercises;
    }

    public void setExercises(List<Exercise> exercises) {
        this.exercises = exercises;
    }

    public List<Assignment> getAssignments() {
        return assignments;
    }

    public void setAssignments(List<Assignment> assignments) {
        this.assignments = assignments;
    }

    public List<Subject> getSubjects() {
        return subjects;
    }

    public void setSubjects(List<Subject> subjects) {
        this.subjects = subjects;
    }

    public Professor() {
    }

    public Professor(Long id, String name, List<Exam> exams, List<Exercise> exercises, List<Assignment> assignments, List<Subject> subjects) {
        this.id = id;
        this.name = name;
        this.exams = exams;
        this.exercises = exercises;
        this.assignments = assignments;
        this.subjects = subjects;
    }
}
