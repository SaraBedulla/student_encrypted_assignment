package com.example.stud_assignment3.entity;

public class AuthenticationResponse {
    private String token;

    public AuthenticationResponse(String token){
        this.token=token;
    }

    public String getToken(){
        return token;
    }
}

