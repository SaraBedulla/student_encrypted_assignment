package com.example.stud_assignment3.controller;

import com.example.stud_assignment3.config.AESUtils;
import com.example.stud_assignment3.entity.AppUser;
import com.example.stud_assignment3.entity.AuthenticationResponse;
import com.example.stud_assignment3.service.AuthenticationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthenticationController {

    private final AuthenticationService authService;


    public AuthenticationController(AuthenticationService authService) {
        this.authService = authService;
    }

    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register(
            @RequestBody AppUser request
    ){
        return  ResponseEntity.ok(authService.register(request));
    }

    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponse> login(
            @RequestBody AppUser request
    ){
        return ResponseEntity.ok(authService.authenticate(request));
    }
}
