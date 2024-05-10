package com.example.stud_assignment3.controller;


import com.example.stud_assignment3.config.AESUtils;
import com.example.stud_assignment3.dto.*;
import com.example.stud_assignment3.entity.*;
import com.example.stud_assignment3.repository.AssignmentRepository;
import com.example.stud_assignment3.repository.StudentRepository;
import com.example.stud_assignment3.service.GradeCalculationService;
import com.example.stud_assignment3.service.JwtService;
import com.example.stud_assignment3.service.StudentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/professor")
public class StudentController {

    private StudentRepository studentRepository;

    private StudentService studentService;

    private GradeCalculationService gradeCalculationService;
    private AssignmentRepository assignmentRepository;

    private JwtService jwtService;

    private AESUtils aesUtils;



    public StudentController(StudentRepository studentRepository, StudentService studentService, AssignmentRepository assignmentRepository, GradeCalculationService gradeCalculationService) {
        this.studentRepository = studentRepository;
        this.studentService = studentService;
        this.assignmentRepository = assignmentRepository;
        this.gradeCalculationService=gradeCalculationService;

    }



    //add a student
    @PostMapping("/add-student")
    public ResponseEntity<Student> addStudentByProfessor(
           @RequestParam String name,
           @RequestParam String email

    ) {
       Student student= studentService.addStudent(name, email);
        return ResponseEntity.status(HttpStatus.CREATED).body(student);
   }



    //add exams to a student
    @PostMapping("/addExam")
    public ResponseEntity<String> addExamToStudent(@RequestParam String email,@RequestBody Set<Exam> examDTO) {
        studentService.addExamToStudent(email,examDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body("Exam added successfully");
    }



    //add assignments to a student
    @PostMapping("/addAssignment")
    public ResponseEntity<String> addAssignmentToStudent(@RequestParam String email,@RequestBody Set<Assignment> assignmentDTO) {
        studentService.addAssignmentToStudent(email, assignmentDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body("Assignment added successfully");
    }



    //add exercises to a student
    @PostMapping("/addExercise")
    public ResponseEntity<String> addExerciseToStudent(@RequestParam String email,@RequestBody Set<Exercise> exerciseDTO) {
        studentService.addExerciseToStudent(email, exerciseDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body("Exercises added successfully");
    }




    //find student by Id
    @GetMapping("/{studentId}")
    public Student findStudent(@PathVariable Long studentId){
        return studentRepository.findById(studentId).orElse(null);
    }



    //find average grade of a student
    @GetMapping("/{studentId}/average-grade")
    public double getStudentAverageGrade(@PathVariable Long studentId) {
        return gradeCalculationService.calculateAverageScoreForStudent(studentId);
    }



    //find exams for a student
    @GetMapping("/{studentId}/exams")
    public Set<ExamDTO> getExamGradesForStudent(@PathVariable Long studentId) {
        return studentService.getExamGradesForStudent(studentId);
    }


    //find assignments for a student
    @GetMapping("/{studentId}/assignments")
    public Set<AssignmentDTO> getAssignmentsGradesForStudent(@PathVariable Long studentId) {
        return studentService.getAssignmentGradesForStudent(studentId);
    }


    //find exercises for a student
    @GetMapping("/{studentId}/exercises")
    public Set<ExerciseDTO> getExercisesGradesForStudent(@PathVariable Long studentId) {
        return studentService.getExerciseGradesForStudent(studentId);
    }



    //delete a student
    @DeleteMapping("/students/{studentId}")
    public void deleteStudent(@PathVariable Long studentId) {
        boolean exists = studentRepository.existsById(studentId);
        if (!exists) {
            throw new IllegalStateException("Student with id " + studentId + " does not exist");
        }
        studentRepository.deleteById(studentId);
    }



    //find average letter grade for a student
    @GetMapping("/{studentId}/average-letter-grade")
    public ResponseEntity<String> getAverageLetterGradeForStudent(@PathVariable Long studentId) {
        try {
            String letterGrade = gradeCalculationService.averageToLetter(studentId);
            return ResponseEntity.ok(letterGrade);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        }


    }


    //find overall average
    @GetMapping("/average")
    public ResponseEntity<Double> getAverageScoreForAllStudents() {
        double averageScore = gradeCalculationService.calculateAverageScoreForAllStudents();
        return ResponseEntity.ok(averageScore);
    }



    //find overall average letter
    @GetMapping("/average-letter")
    public ResponseEntity<String> getAverageLetterGradeForAll() {
        try {
            String letterGrade = gradeCalculationService.averageAllToLetter();
            return ResponseEntity.ok(letterGrade);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        }
    }



    //find assignments average for all students
    @GetMapping("allStudents/assignments")
    public AssignmentAverageScoreDTO calculateAverageScoreForAssignmentsAllStudents() {
        return gradeCalculationService.calculateAverageScoreForAssignmentsAllStudents();
    }



    //find exams average for all students
    @GetMapping("/allStudents/exams")
    public double calculateExamsAveragesForAllStudents() {
        return gradeCalculationService.calculateAverageScoreForExamsAllStudents();
    }



    //find exercises average for all students
    @GetMapping("/allStudents/exercises")
    public double calculateExercisesAveragesForAllStudents() {
        return gradeCalculationService.calculateAverageScoreForExercisesAllStudents();
    }



    //update student by id
    @PutMapping("/{id}")
    public ResponseEntity<String> updateStudent(@PathVariable("id") Long studentId, @RequestBody Student updatedStudent) {
        try {
            studentService.updateStudent(studentId, updatedStudent);
            return ResponseEntity.ok("Student updated successfully");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred while updating the student");
        }
    }



    //get all students
    @GetMapping
    public List<StudentDTO> findAllStudentsWithGrades()  {
        return studentService.findAllStudentsWithGrades();
    }
    }

