package com.example.stud_assignment3.service;

import com.example.stud_assignment3.entity.AppUser;
import com.example.stud_assignment3.entity.AuthenticationResponse;
import com.example.stud_assignment3.entity.Professor;
import com.example.stud_assignment3.entity.Role;
import com.example.stud_assignment3.repository.ProfessorRepository;
import com.example.stud_assignment3.repository.User5Repository;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {

    private final User5Repository user5Repository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    private final ProfessorRepository professorRepository;
    private final AuthenticationManager authenticationManager;

    private final StudentService studentService;

    public AuthenticationService(User5Repository user5Repository, PasswordEncoder passwordEncoder, JwtService jwtService, AuthenticationManager authenticationManager,ProfessorRepository professorRepository, StudentService studentService) {
        this.user5Repository = user5Repository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
        this.authenticationManager = authenticationManager;
        this.professorRepository=professorRepository;
        this.studentService=studentService;
    }

    public AuthenticationResponse register(AppUser request){

        if (request.getRole() != Role.PROFESSOR) {
            throw new IllegalArgumentException("Only professors are allowed to register.");
        }
        AppUser appUser = new AppUser();
        appUser.setFirstName(request.getFirstName());
        appUser.setLastName(request.getLastName());
        appUser.setUsername(request.getUsername());
        appUser.setPassword(passwordEncoder.encode(request.getPassword()));

        appUser.setRole(request.getRole());

        appUser =user5Repository.save(appUser);

        Professor professor = new Professor();
        professor.setName(appUser.getFirstName() + " " + appUser.getLastName());
        professor = professorRepository.save(professor);


        String token= jwtService.generateToken(appUser);

        return new AuthenticationResponse(token);
    }

    public AuthenticationResponse authenticate(AppUser request){
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getUsername(),
                        request.getPassword()
                )
        );

        AppUser appUser =user5Repository.findByUsername(request.getUsername()).orElseThrow();
        String token=jwtService.generateToken(appUser);
        return new AuthenticationResponse(token);
    }


}
