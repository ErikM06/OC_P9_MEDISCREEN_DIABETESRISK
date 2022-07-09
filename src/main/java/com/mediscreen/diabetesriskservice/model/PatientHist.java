package com.mediscreen.diabetesriskservice.model;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.springframework.stereotype.Component;


@Data
@Component
public class PatientHist {


    private String id;

    @JsonProperty ("patId")
    private long patId;

    @JsonProperty ("recommendations")
    private String content;


    @JsonProperty("Patient")
    private String lastName;



    public PatientHist() {
    }

    public PatientHist(String lastName, String content) {
        this.content = content;
        this.lastName = lastName;

    }

    public PatientHist(String id, long patId, String content, String lastName) {
        this.id = id;
        this.patId = patId;
        this.content = content;
        this.lastName = lastName;
    }

    public PatientHist(String id, long patId, String lastName) {
        this.id = id;
        this.patId = patId;
        this.lastName = lastName;
    }

    @Override
    public String toString() {
        return "Patient{" +
                "id=" + id +
                ", content='" + content + '\'' +
                '}';
    }


}
