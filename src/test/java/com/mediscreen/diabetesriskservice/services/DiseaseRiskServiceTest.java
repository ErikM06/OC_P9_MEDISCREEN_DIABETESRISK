package com.mediscreen.diabetesriskservice.services;

import com.mediscreen.diabetesriskservice.customExceptions.PatIdNotFoundException;
import com.mediscreen.diabetesriskservice.dto.PatientAssessmentDTO;
import com.mediscreen.diabetesriskservice.model.Patient;
import com.mediscreen.diabetesriskservice.proxy.PatientClientProxy;
import com.mediscreen.diabetesriskservice.proxy.PatientHistClientProxy;
import com.mediscreen.diabetesriskservice.services.util.FamilyTypes;
import com.mediscreen.diabetesriskservice.services.util.WordsTrigger;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyLong;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class DiseaseRiskServiceTest {

    Logger logger = LoggerFactory.getLogger(DiseaseRiskService.class);

    @Autowired
    DiseaseRiskService diseaseRiskService;

    @MockBean
    PatientClientProxy patientClientProxy;

    @MockBean
    PatientHistClientProxy patientHistClientProxy;

    @MockBean
    CalculateTriggerService calculateTriggerService;




    private final Patient patientTest = new Patient("firstnameTest","lastnameTest", Date.from(Instant.ofEpochMilli(System.currentTimeMillis())),
            "F","addressTest", "phoneTest", null);

    @Test
    void getDiseaseRiskTest () throws PatIdNotFoundException {
        List<String> wordsTrigger = WordsTrigger.listOfWordTriggers;
        List<String> randomsWordsTrigger = new ArrayList<>();

        List<String> familyType = FamilyTypes.familyTypeList;


        int random = ThreadLocalRandom.current().nextInt(0,wordsTrigger.size());
        //extract a random number of wordsTrigger from WordsTrigger interface
        logger.info("random : "+random);
        for(int i =0; i<random; i++){
            randomsWordsTrigger.add((wordsTrigger.get(i)));
            logger.info("wordsTrigger is:" + wordsTrigger.get(i));
        }


        Mockito.when(calculateTriggerService.getTriggerCount(any(Long.class))).thenReturn(randomsWordsTrigger.size());

        this.patientTest.setId(1L);
        String diabetesRisk =  diseaseRiskService.getDiseaseRisk(this.patientTest);
        logger.info(diabetesRisk);
        assertTrue(familyType.stream().anyMatch(w -> w.equals(diabetesRisk)));
    }

    @Test
    void diabetesAssessmentByFamilyName(){

        this.patientTest.setId(1L);
        List<Patient> patientList = new ArrayList<>(List.of(this.patientTest));
        Mockito.when(patientClientProxy.getPatientByFamily(this.patientTest.getLastName())).thenReturn(patientList);
        List<PatientAssessmentDTO> patientAssessmentDTOList = diseaseRiskService.diabetesAssessmentByFamilyName(this.patientTest.getLastName());
        assertEquals(1, patientAssessmentDTOList.size());
    }

    @Test
    void diabetesAssessmentById(){
        Patient patientTest2 = this.patientTest;
        patientTest2.setId(2L);

        Mockito.when(patientClientProxy.getPatientById(patientTest2.getId())).thenReturn(patientTest2);

        PatientAssessmentDTO patientAssessmentDTOList = diseaseRiskService.diabetesAssessmentById(patientTest2.getId());
        assertEquals(patientAssessmentDTOList.getLastName(),patientTest2.getLastName());

    }
}
