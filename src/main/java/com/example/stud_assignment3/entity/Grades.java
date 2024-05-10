package com.example.stud_assignment3.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="GRADE")
public class Grades {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private int lowerBound;
    private int upperBound;
    private String letterGrade;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getLowerBound() {
        return lowerBound;
    }

    public void setLowerBound(int lowerBound) {
        this.lowerBound = lowerBound;
    }

    public int getUpperBound() {
        return upperBound;
    }

    public void setUpperBound(int upperBound) {
        this.upperBound = upperBound;
    }

    public String getLetterGrade() {
        return letterGrade;
    }

    public void setLetterGrade(String letterGrade) {
        this.letterGrade = letterGrade;
    }

    public Grades() {

    }

    public Grades(Long id, int lowerBound, int upperBound, String letterGrade) {
        this.id = id;
        this.lowerBound = lowerBound;
        this.upperBound = upperBound;
        this.letterGrade = letterGrade;
    }
}
