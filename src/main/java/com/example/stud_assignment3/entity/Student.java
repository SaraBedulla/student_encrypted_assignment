package com.example.stud_assignment3.entity;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="STUDENT")
public class Student {
    @Id
    @GeneratedValue
    private Long id;

    private String name;



    private String email;


//    @OneToOne
//    @JoinColumn(name = "user_id", referencedColumnName = "id")
//    private AppUser appUser;

    @OneToOne(mappedBy = "student", cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private AppUser appUser;



    @ManyToMany(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    @JoinTable(name="STUDENT_ASSIGNMENT_TABLE",
    joinColumns = {
           @JoinColumn(name = "student_id", referencedColumnName = "id")
    },
    inverseJoinColumns = {
            @JoinColumn(name="assignment_id",referencedColumnName = "id")
    }
    )

    private Set<Assignment> assignments= new HashSet<>();


    @ManyToMany(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    @JoinTable(name="STUDENT_EXAM_TABLE",
            joinColumns = {
                    @JoinColumn(name = "student_id", referencedColumnName = "id")
            },
            inverseJoinColumns = {
                    @JoinColumn(name="exam_id",referencedColumnName = "id")
            }
    )

    private Set<Exam> exams= new HashSet<>();


    @ManyToMany(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    @JoinTable(name="STUDENT_EXERCISE_TABLE",
            joinColumns = {
                    @JoinColumn(name = "student_id", referencedColumnName = "id")
            },
            inverseJoinColumns = {
                    @JoinColumn(name="exercise_id",referencedColumnName = "id")
            }
    )

    private Set<Exercise> exercises= new HashSet<>();




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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public AppUser getUser5() {
        return appUser;
    }

    public void setUser5(AppUser appUser) {
        this.appUser = appUser;
    }

    public Set<Assignment> getAssignments() {
        return assignments;
    }

    public void setAssignments(Set<Assignment> assignments) {
        this.assignments = assignments;
    }

    public Set<Exam> getExams() {
        return exams;
    }

    public void setExams(Set<Exam> exams) {
        this.exams = exams;
    }

    public Set<Exercise> getExercises() {
        return exercises;
    }

    public void setExercises(Set<Exercise> exercises) {
        this.exercises = exercises;
    }

    public Student() {
    }

    public Student(Long id, String name, String email, AppUser appUser, Set<Assignment> assignments, Set<Exam> exams, Set<Exercise> exercises) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.appUser = appUser;
        this.assignments = assignments;
        this.exams = exams;
        this.exercises = exercises;
    }
}
