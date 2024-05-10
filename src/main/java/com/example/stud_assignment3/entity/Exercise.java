package com.example.stud_assignment3.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Entity
@Table(name="EXERCISE")
public class Exercise {
    @Id
    @GeneratedValue
    private Long id;

    private double score;



    @ManyToMany(mappedBy = "exercises", fetch=FetchType.LAZY)

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

    public Exercise(Professor professor, Subject subject,double score) {
        this.professor = professor;
        this.subject=subject;
        this.score=score;
    }

    public Exercise(Long id, double score, Set<Student> students, Professor professor, Subject subject, Grades grade) {
        this.id = id;
        this.score = score;
        this.students = students;
        this.professor = professor;
        this.subject = subject;
        this.grade = grade;
    }

    public Exercise() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
