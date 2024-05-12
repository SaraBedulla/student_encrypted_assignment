package com.example.stud_assignment3.service;

import com.example.stud_assignment3.config.AESUtils;
import com.example.stud_assignment3.dto.*;
import com.example.stud_assignment3.entity.*;
import com.example.stud_assignment3.repository.*;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class StudentService {



    private final StudentRepository studentRepository;
    private final ExamRepository examRepository;
    private final AssignmentRepository assignmentRepository;
    private final ExerciseRepository exerciseRepository;

    private final User5Repository user5Repository;
    private JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    private final PasswordEncoder passwordEncoder;

    private final StudentDTO studentDTO;
    private final AESUtils aesUtils;

    private final EncryptionService encryptionService;

    private final EncryptedStudentRepository encryptedStudentRepository;



    @Autowired
    public StudentService(StudentRepository studentRepository, ExamRepository examRepository, AssignmentRepository assignmentRepository, ExerciseRepository exerciseRepository, User5Repository user5Repository, AuthenticationManager authenticationManager, PasswordEncoder passwordEncoder, StudentDTO studentDTO, AESUtils aesUtils, EncryptionService encryptionService, EncryptedStudentRepository encryptedStudentRepository) {
        this.studentRepository = studentRepository;
        this.examRepository = examRepository;
        this.assignmentRepository = assignmentRepository;
        this.exerciseRepository = exerciseRepository;
        this.user5Repository = user5Repository;
        this.authenticationManager = authenticationManager;
        this.passwordEncoder = passwordEncoder;
        this.studentDTO = studentDTO;

        this.aesUtils = aesUtils;
        this.encryptionService = encryptionService;
        this.encryptedStudentRepository = encryptedStudentRepository;
    }





    public Set<ExamDTO> getExamGradesForStudent(Long studentId) {
        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new IllegalArgumentException("Student not found"));

        Set<Exam> exams = student.getExams();

        return exams.stream()
                .map(exam -> new ExamDTO( exam.getScore(),  mapToSubjectDTO(exam.getSubject())))
                .collect(Collectors.toSet());
    }



    public Set<ExamDTO> getExamGradesForStudent2(String studentEmail) {
        Student student = studentRepository.findStudentByEmail(studentEmail)
                .orElseThrow(() -> new IllegalArgumentException("Student not found"));

        Set<Exam> exams = student.getExams();

        return exams.stream()
                .map(exam -> new ExamDTO( exam.getScore(),  mapToSubjectDTO(exam.getSubject())))
                .collect(Collectors.toSet());
    }



    private SubjectDTO mapToSubjectDTO(Subject subject) {
        if (subject == null) {
            return null;
        }
        return new SubjectDTO(subject.getId(), subject.getName());
    }




    public Set<AssignmentDTO> getAssignmentGradesForStudent(Long studentId) {
        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new IllegalArgumentException("Student not found"));

        Set<Assignment> assignments = student.getAssignments();

        return assignments.stream()
                .map(assignment -> new AssignmentDTO(assignment.getScore(), assignment.getTitle()))
                .collect(Collectors.toSet());
    }

    public Set<AssignmentDTO> getAssignmentGradesForStudent2(String studentEmail) {
        Student student = studentRepository.findStudentByEmail(studentEmail)
                .orElseThrow(() -> new IllegalArgumentException("Student not found"));

        Set<Assignment> assignments = student.getAssignments();

        return assignments.stream()
                .map(assignment -> new AssignmentDTO(assignment.getScore(), assignment.getTitle()))
                .collect(Collectors.toSet());
    }


    public Set<ExerciseDTO> getExerciseGradesForStudent(Long studentId) {
        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new IllegalArgumentException("Student not found"));

        Set<Exercise> exercises = student.getExercises();
        return exercises.stream()
                .map(exercise -> new ExerciseDTO( exercise.getScore(), mapToSubjectDTO(exercise.getSubject())))
                .collect(Collectors.toSet());
    }

    public Set<ExerciseDTO> getExerciseGradesForStudent2(String studentEmail) {
        Student student = studentRepository.findStudentByEmail(studentEmail)
                .orElseThrow(() -> new IllegalArgumentException("Student not found"));

        Set<Exercise> exercises = student.getExercises();
        return exercises.stream()
                .map(exercise -> new ExerciseDTO( exercise.getScore(), mapToSubjectDTO(exercise.getSubject())))
                .collect(Collectors.toSet());
    }



    @Transactional
    public void addAssignmentToStudent(String email, Set<Assignment> assignmentDTO) {
        Student student = studentRepository.findStudentByEmail(email).orElseThrow(() -> new RuntimeException("Student not found"));
        for (Assignment assignmentDTO1 : assignmentDTO) {
            Assignment assignment = new Assignment();
            assignment.setScore(assignmentDTO1.getScore());
            String assignment2=assignmentDTO1.getTitle();
            assignment.setTitle(assignment2);
            //assignment.setStudents(student);
            assignmentRepository.save(assignment);

        }
        student.setAssignments(assignmentDTO);
    }



    @Transactional
    public void addExerciseToStudent(String email, Set<Exercise> exerciseDTO) {
        Student student = studentRepository.findStudentByEmail(email).orElseThrow(() -> new RuntimeException("Student not found"));
        for (Exercise exerciseDTO1 : exerciseDTO) {
            Exercise exercise = new Exercise();
            exercise.setScore(exerciseDTO1.getScore());

            //assignment.setStudent(student);
            exerciseRepository.save(exercise);

        }
        student.setExercises(exerciseDTO);
    }


    @Transactional
    public void addExamToStudent(String email, Set<Exam> examDTO) {
        Student student = studentRepository.findStudentByEmail(email).orElseThrow(() -> new RuntimeException("Student not found"));

        for(Exam examDTO1:examDTO){
        Exam exam = new Exam();
        exam.setScore(examDTO1.getScore());
        exam.setSubject(examDTO1.getSubject());
        examRepository.save(exam);

    }
        student.setExams(examDTO);
    }




    public Student addStudent(String name, String email) {

       Optional<AppUser> existingUser = user5Repository.findByUsername(email);
      if (existingUser.isPresent()) {
            throw new IllegalStateException("Email is already registered as a user.");
        }


      //String encryptedName=aesUtils.encrypt(name);
      AppUser appUser = new AppUser();
      appUser.setFirstName(name);
      appUser.setUsername(email);
      appUser.setPassword(passwordEncoder.encode(email));
      appUser.setRole(Role.STUDENT);


        appUser = user5Repository.save(appUser);



        Student student = new Student();
        student.setName(name
                );
        student.setEmail(email);
        //student.setId(Long.valueOf(studentId));
        student.setUser5(appUser);


        return studentRepository.save(student);
    }



    @Transactional
    public void updateStudent(Long studentId, Student updatedStudent) {
        Optional<Student> optionalStudent = studentRepository.findById(studentId);
        if (optionalStudent.isPresent()) {
            Student existingStudent = optionalStudent.get();

            existingStudent.setName(updatedStudent.getName());
            existingStudent.setEmail(updatedStudent.getEmail());
            existingStudent.setAssignments(updatedStudent.getAssignments());
            existingStudent.setExams(updatedStudent.getExams());
            existingStudent.setExercises(updatedStudent.getExercises());

            studentRepository.save(existingStudent);
        } else {

            throw new IllegalArgumentException("Student not found with ID: " + studentId);
        }
    }

    public List<StudentDTO> findAllStudentsWithGrades()  {
        List<StudentDTO> list = new ArrayList<>();

        List<Student> students = studentRepository.findAll();
        for (Student student : students) {
            long id = student.getId();
            String decryptedName = encryptionService.decryptHex(student.getName());

            String email = student.getEmail();


            Map<String, Double> examScores = new HashMap<>();
            for (Exam exam : student.getExams()) {
             examScores.put(exam.getSubject().getName(), exam.getScore());
          }

            Map<String, Double> assignmentScores = new HashMap<>();
            for (Assignment assignment : student.getAssignments()) {
                assignmentScores.put(assignment.getTitle(), assignment.getScore());
            }

           Map<String, Double> exerciseScores = new HashMap<>();
            for (Exercise exercise : student.getExercises()) {
             exerciseScores.put("Exercise Score", exercise.getScore());
            }

            StudentDTO studentDTO = new StudentDTO(id, decryptedName, email, examScores, assignmentScores, exerciseScores);
            list.add(studentDTO);
        }

        return list;
    }


    public List<String> getAllDecryptedNames() {
        return encryptedStudentRepository.findAllDecryptedNames();
    }

    }



