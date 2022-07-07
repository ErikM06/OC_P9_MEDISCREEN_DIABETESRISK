package com.mediscreen.diabetesriskservice.proxy;


import com.mediscreen.diabetesriskservice.model.PatientHist;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;


@FeignClient(value = "mediscreen-hist", url ="http://localhost:8082/pat-history/")
public interface PatientHistClientProxy {


    @GetMapping ("/get-by-id")
    PatientHist getPatientHistById (@RequestParam String id);

    @GetMapping ("/get-by-pat-id")
    List<PatientHist> getPatientHistByPatId (@RequestParam Long id);

    @GetMapping ("/get-all-patient-history")
    List<PatientHist> getAllPatientsHist();

}
