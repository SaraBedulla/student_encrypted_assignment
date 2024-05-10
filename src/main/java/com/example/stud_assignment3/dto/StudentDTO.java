package com.example.stud_assignment3.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.util.Map;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Component
public class StudentDTO {

    private Long id;
    private String name;
    private String email;
    private Map<String, Double> examScores;  // Exam name to Score mapping
    private Map<String, Double> assignmentScores;  // Assignment title to Score mapping
    private Map<String, Double> exerciseScores;  // Exercise score mapping



}
