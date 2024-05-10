package com.example.stud_assignment3.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name="SUBJECT")
public class Subject {
    @Id
    @GeneratedValue
    private Long id;
    private String name;

    @OneToMany(cascade = CascadeType.ALL,mappedBy = "subject")
    private List<Exam> exams;

    @OneToMany(cascade = CascadeType.ALL,mappedBy = "subject")
    private List<Exercise> exercises;

    @OneToMany(cascade = CascadeType.ALL,mappedBy = "subject")
    private List<Assignment> assignments;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="professor_id")
    private Professor professor;

    public Subject(String name) {
        this.name=name;


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

    public Professor getProfessor() {
        return professor;
    }

    public void setProfessor(Professor professor) {
        this.professor = professor;
    }

    public Subject() {
    }

    public Subject(Long id, String name, List<Exam> exams, List<Exercise> exercises, List<Assignment> assignments, Professor professor) {
        this.id = id;
        this.name = name;
        this.exams = exams;
        this.exercises = exercises;
        this.assignments = assignments;
        this.professor = professor;
    }
}
