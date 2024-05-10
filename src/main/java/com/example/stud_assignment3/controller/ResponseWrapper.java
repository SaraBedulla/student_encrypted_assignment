package com.example.stud_assignment3.controller;

import com.example.stud_assignment3.entity.Student;

import java.util.HashSet;
import java.util.List;

public class ResponseWrapper<T> {
    private boolean success;
    private String message;
    private List<T> content;

    public ResponseWrapper() {

    }

    public ResponseWrapper(boolean success, String message, List<T> content) {
        this.success = success;
        this.message = message;
        this.content = content;
    }


    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<T> getData() {
        return content;
    }

    public void setData(List<Student> data) {
        this.content = content;
    }
}
