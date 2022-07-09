package com.mediscreen.diabetesriskservice.proxy;


import com.mediscreen.diabetesriskservice.model.PatientHist;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;


@FeignClient(value = "mediscreen-notes", url ="http://localhost:8082/pat-notes/")
public interface PatientNoteClientProxy {


    @GetMapping ("/get-by-id")
    PatientHist getPatientNotesById(@RequestParam String id);

    @GetMapping ("/get-by-pat-id")
    List<PatientHist> getPatientNotesByPatId(@RequestParam Long id);

    @GetMapping ("/get-all-patient-notes")
    List<PatientHist> getAllPatientNotes();

}
