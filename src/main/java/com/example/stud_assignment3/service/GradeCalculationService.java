package com.example.stud_assignment3.service;

import com.example.stud_assignment3.dto.AssignmentAverageScoreDTO;
import com.example.stud_assignment3.entity.*;
import com.example.stud_assignment3.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GradeCalculationService {
    private final StudentRepository studentRepository;
    private final AssignmentRepository assignmentRepository;
    private final ExamRepository examRepository;
    private final ExerciseRepository exerciseRepository;
    private final GradesRepository gradesRepository;


    @Autowired
    public GradeCalculationService(StudentRepository studentRepository, AssignmentRepository assignmentRepository, ExamRepository examRepository, ExerciseRepository exerciseRepository, GradesRepository gradesRepository) {
        this.studentRepository = studentRepository;
        this.assignmentRepository = assignmentRepository;
        this.examRepository = examRepository;
        this.exerciseRepository = exerciseRepository;
        this.gradesRepository = gradesRepository;
    }

    public String convertScoreToGrade(double score) {
        Grades grade = gradesRepository.findAll()
                .stream()
                .filter(g -> g.getLowerBound() <= score && score <= g.getUpperBound())
                .findFirst()
                .orElse(null);

        return grade != null ? grade.getLetterGrade() : "N/A";
    }

    public double calculateAverageScoreForStudent2(String studentEmail) {
        Student student = studentRepository.findStudentByEmail(studentEmail).orElse(null);
        if (student == null) {
            throw new IllegalArgumentException("Student not found");
        }

        double totalScore = 0;
        int count = 0;

        for (Assignment assignment : student.getAssignments()) {
            totalScore += assignment.getScore();
            count++;
        }

        for (Exam exam : student.getExams()) {
            totalScore += exam.getScore();
            count++;
        }

        for (Exercise exercise : student.getExercises()) {
            totalScore += exercise.getScore();
            count++;
        }

        return count > 0 ? totalScore / count : 0;
    }


    public double calculateAverageScoreForStudent(Long studentId) {
        Student student = studentRepository.findById(studentId).orElse(null);
        if (student == null) {
            throw new IllegalArgumentException("Student not found");
        }

        double totalScore = 0;
        int count = 0;

        for (Assignment assignment : student.getAssignments()) {
            totalScore += assignment.getScore();
            count++;
        }

        for (Exam exam : student.getExams()) {
            totalScore += exam.getScore();
            count++;
        }

        for (Exercise exercise : student.getExercises()) {
            totalScore += exercise.getScore();
            count++;
        }

        return count > 0 ? totalScore / count : 0;
    }



    public String averageToLetter(Long studentId){
        double d=calculateAverageScoreForStudent(studentId);
        return String.valueOf(convertScoreToGrade(d));
    }


    public String averageToLetter2(String studentEmail){
        double d=calculateAverageScoreForStudent2(studentEmail);
        return String.valueOf(convertScoreToGrade(d));
    }




    public double calculateAverageScoreForAllStudents() {
       List<Student> students = studentRepository.findAll();
       int totalStudents = students.size();
       double totalScore = 0;

       for (Student student : students) {
           totalScore += calculateAverageScoreForStudent(student.getId());
       }

       return totalStudents > 0 ? totalScore / totalStudents : 0;
   }


   public String averageAllToLetter(){
       return String.valueOf(convertScoreToGrade(calculateAverageScoreForAllStudents()));
    }



    public AssignmentAverageScoreDTO calculateAverageScoreForAssignmentsAllStudents() {
         List<Student> students = studentRepository.findAll();
            int totalAssignments = 0;
            double totalScore = 0;

        for (Student student : students) {
            for (Assignment assignment : student.getAssignments()) {
                 totalScore += assignment.getScore();
                 totalAssignments++;
            }
        }

    double averageScore = totalAssignments > 0 ? totalScore / totalAssignments : 0;
    return new AssignmentAverageScoreDTO(averageScore);
}


    public double calculateAverageScoreForExamsAllStudents() {
        List<Student> students = studentRepository.findAll();
            int totalExams = 0;
            double totalScore = 0;

            for (Student student : students) {
                for (Exam exam : student.getExams()) {
                     totalScore += exam.getScore();
                     totalExams++;
                }
            }

        return totalExams > 0 ? totalScore / totalExams : 0;
    }


    public double calculateAverageScoreForExercisesAllStudents() {
        List<Student> students = studentRepository.findAll();
        int totalExercises = 0;
        double totalScore = 0;

        for (Student student : students) {
            for (Exercise exercise : student.getExercises()) {
                totalScore += exercise.getScore();
                totalExercises++;
            }
        }

        return totalExercises > 0 ? totalScore / totalExercises : 0;
    }




}


