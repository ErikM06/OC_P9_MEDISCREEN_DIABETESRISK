package com.mediscreen.diabetesriskservice.controller;


import com.mediscreen.diabetesriskservice.dto.PatientAssessmentDTO;
import com.mediscreen.diabetesriskservice.model.Patient;
import com.mediscreen.diabetesriskservice.proxy.PatientClientProxy;
import com.mediscreen.diabetesriskservice.services.DiseaseRiskService;
import com.mediscreen.diabetesriskservice.services.util.CalculateAgeFromDob;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;


@RestController
@RequestMapping ("/assess")
public class DiseaseRiskController {

    Logger logger = LoggerFactory.getLogger(DiseaseRiskController.class);

    @Autowired
    DiseaseRiskService diseaseRiskService;



    @GetMapping("/id")
    public PatientAssessmentDTO getAssessById (@RequestParam Long id){
        PatientAssessmentDTO patientAssessmentDTO = new PatientAssessmentDTO();
        try {
         patientAssessmentDTO = diseaseRiskService.diabetesAssessementById(id);

        } catch (Exception e){
            e.getMessage();
        }
        return patientAssessmentDTO;
    }

    @GetMapping("/familyName")
    public List<PatientAssessmentDTO> getAssessByFamilyName (@RequestParam String familyName){
            List<PatientAssessmentDTO> patientAssessmentDTOTolist = new ArrayList<>();
            try {
                patientAssessmentDTOTolist = diseaseRiskService.diabetesAssessementByfamilyName(familyName);
            } catch (Exception e){
                e.getMessage();
            }

        return patientAssessmentDTOTolist;
    }
}
