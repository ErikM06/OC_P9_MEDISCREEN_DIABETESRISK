package com.mediscreen.diabetesriskservice.controller;


import com.mediscreen.diabetesriskservice.customExceptions.FamilyNameNotFoundException;
import com.mediscreen.diabetesriskservice.customExceptions.PatIdNotFoundException;
import com.mediscreen.diabetesriskservice.dto.PatientAssessmentDTO;
import com.mediscreen.diabetesriskservice.services.DiseaseRiskService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping ("/assess")
public class DiseaseRiskController {

    Logger logger = LoggerFactory.getLogger(DiseaseRiskController.class);

    @Autowired
    DiseaseRiskService diseaseRiskService;


    /**
     *
     * @param id
     * @return a patientAssessmentDTO, only few field from Patient.class to match view requirements
     * basic Patient info + assessment on diabetes risk
     * @throws PatIdNotFoundException
     */
    @GetMapping("/id")
    public PatientAssessmentDTO getAssessById (@RequestParam Long id) throws PatIdNotFoundException {
        PatientAssessmentDTO patientAssessmentDTO = new PatientAssessmentDTO();
        try {
            patientAssessmentDTO = diseaseRiskService.diabetesAssessmentById(id);
        } catch (PatIdNotFoundException e){
            throw new PatIdNotFoundException("No patient with Id: "+id);
        }
        return patientAssessmentDTO;
    }

    /**
     *
     * @param familyName
     * @returna a list of patientAssessmentDTO, only few field from Patient.class to match view requirements
     * basic Patient info + assessment on diabetes risk
     * @throws FamilyNameNotFoundException
     */
    @GetMapping("/familyName")
    public List<PatientAssessmentDTO> getAssessByFamilyName (@RequestParam String familyName) throws FamilyNameNotFoundException {
        List<PatientAssessmentDTO> patientAssessmentDTOToList;
        try {
            patientAssessmentDTOToList = diseaseRiskService.diabetesAssessmentByFamilyName(familyName);
        } catch (FamilyNameNotFoundException e){
            throw new FamilyNameNotFoundException("No patient with FamilyName "+ familyName);
        }

        return patientAssessmentDTOToList;
    }
}
