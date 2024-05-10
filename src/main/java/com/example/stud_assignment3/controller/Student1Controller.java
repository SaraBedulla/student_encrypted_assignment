package com.example.stud_assignment3.controller;


import com.example.stud_assignment3.dto.AssignmentDTO;
import com.example.stud_assignment3.dto.ExamDTO;
import com.example.stud_assignment3.dto.ExerciseDTO;
import com.example.stud_assignment3.entity.Assignment;
import com.example.stud_assignment3.repository.AssignmentRepository;
import com.example.stud_assignment3.repository.StudentRepository;
import com.example.stud_assignment3.service.GradeCalculationService;
import com.example.stud_assignment3.service.JwtService;
import com.example.stud_assignment3.service.StudentService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/student")
public class Student1Controller {
    private StudentRepository studentRepository;

    private StudentService studentService;

    private GradeCalculationService gradeCalculationService;
    private AssignmentRepository assignmentRepository;

    private JwtService jwtService;

    public Student1Controller(StudentRepository studentRepository, StudentService studentService, GradeCalculationService gradeCalculationService, AssignmentRepository assignmentRepository, JwtService jwtService) {
        this.studentRepository = studentRepository;
        this.studentService = studentService;
        this.gradeCalculationService = gradeCalculationService;
        this.assignmentRepository = assignmentRepository;
        this.jwtService = jwtService;
    }




    //get my average
    @GetMapping("/myAverage")
    public ResponseEntity<Double> getMyAverage(HttpServletRequest request){
        String token=request.getHeader("Authorization").substring(7);
        String username=jwtService.extractUsername(token);

        double myAverage=gradeCalculationService.calculateAverageScoreForStudent2(username);
        return ResponseEntity.ok(myAverage);
    }



    //get my letter average
    @GetMapping("/myLetterAverage")
    public ResponseEntity<String> getMyLetterAverage(HttpServletRequest request){
        String token=request.getHeader("Authorization").substring(7);
        String username=jwtService.extractUsername(token);

        String myLetterAverage=gradeCalculationService.averageToLetter2(username);
        return ResponseEntity.ok(myLetterAverage);
    }




    //get my exams grades
    @GetMapping("/myExams")
    public ResponseEntity<Set<ExamDTO>> getMyExams(HttpServletRequest request){
        String token=request.getHeader("Authorization").substring(7);
        String username=jwtService.extractUsername(token);

        Set<ExamDTO> myExams=studentService.getExamGradesForStudent2(username);
        return ResponseEntity.ok(myExams);
    }




    //get my exercises grades
    @GetMapping("/myExercises")
    public ResponseEntity<Set<ExerciseDTO>> getMyExercises(HttpServletRequest request){
        String token=request.getHeader("Authorization").substring(7);
        String username=jwtService.extractUsername(token);

        Set<ExerciseDTO> myExercises=studentService.getExerciseGradesForStudent2(username);
        return ResponseEntity.ok(myExercises);
    }



    //get my assignments grades
    @GetMapping("/myAssignments")
    public ResponseEntity<Set<AssignmentDTO>> getMyAssignments(HttpServletRequest request){
        String token=request.getHeader("Authorization").substring(7);
        String username=jwtService.extractUsername(token);

        Set<AssignmentDTO> myAssignments=studentService.getAssignmentGradesForStudent2(username);
        return ResponseEntity.ok(myAssignments);
    }


}

