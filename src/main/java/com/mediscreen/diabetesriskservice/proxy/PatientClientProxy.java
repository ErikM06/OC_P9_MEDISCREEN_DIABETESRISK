package com.mediscreen.diabetesriskservice.proxy;


import com.mediscreen.diabetesriskservice.model.Patient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient (name = "mediscreen-patient", url = "http://localhost:8081/patient/")
public interface PatientClientProxy {

    @GetMapping (value = "/getById")
    Patient getPatientById (@RequestParam Long id);

    @GetMapping(value = "/getPatientList", consumes = MediaType.APPLICATION_JSON_VALUE)
    List<Patient> getPatientList ();

    @GetMapping ("/getPatientByFamily")
    List<Patient> getPatientByFamily (@RequestParam String family);


}
