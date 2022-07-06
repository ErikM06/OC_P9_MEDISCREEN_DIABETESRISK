package com.mediscreen.diabetesriskservice.proxy;


import com.mediscreen.diabetesriskservice.model.PatientHist;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient (value = "mediscreen-hist", url ="http://localhost:8082/patHistory/")
public interface PatientHistClientProxy {


    @GetMapping ("/getById")
    PatientHist getPatientHistById (@RequestParam String id);

    @GetMapping ("/getByPatId")
    List<PatientHist> getPatientHistByPatId (@RequestParam Long id);

    @GetMapping ("/getAllPatientHistory")
    List<PatientHist> getAllPatientsHist();

}
