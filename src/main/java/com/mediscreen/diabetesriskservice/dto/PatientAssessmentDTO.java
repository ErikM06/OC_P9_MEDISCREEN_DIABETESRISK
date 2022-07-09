package com.mediscreen.diabetesriskservice.dto;

import org.springframework.stereotype.Component;


@Component

public class PatientAssessmentDTO {

    private String firstName;

    private String lastName;

    private int age;

    private String assessment;

    public PatientAssessmentDTO(String firstName, String lastName, int age, String assessment){
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.assessment = assessment;
    }
    public PatientAssessmentDTO(){

    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getAssessment() {
        return assessment;
    }

    public void setAssessment(String assessment) {
        this.assessment = assessment;
    }
}
