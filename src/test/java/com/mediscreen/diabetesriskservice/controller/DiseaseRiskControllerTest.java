package com.mediscreen.diabetesriskservice.controller;

import com.mediscreen.diabetesriskservice.DiabetesRiskServiceApplication;
import com.mediscreen.diabetesriskservice.dto.PatientAssessmentDTO;
import com.mediscreen.diabetesriskservice.model.Patient;
import com.mediscreen.diabetesriskservice.services.DiseaseRiskService;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(DiseaseRiskController.class)
@ContextConfiguration(classes ={DiabetesRiskServiceApplication.class})
public class DiseaseRiskControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private DiseaseRiskService diseaseRiskService;

    @Test
    public void getAssessByIdTest_shouldReturns_200() throws Exception {
        Patient patient = new Patient();

        patient.setId(1L);
        int age = ThreadLocalRandom.current().nextInt();
        String diabetesAssess = "diabetesAssessement";
        PatientAssessmentDTO patientAssessmentDTO = new PatientAssessmentDTO("GivenTest","FamilyTypeTest", age,
               diabetesAssess );

        Mockito.when(diseaseRiskService.diabetesAssessmentById(patient.getId())).thenReturn(patientAssessmentDTO);

        mvc.perform(get("/assess/id"+"?id="+1L)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();

    }

    @Test
    public void getAssessByFamilyNameTest_shouldReturns_200() throws Exception {
        String familyName = "testBorderline";

        int age = ThreadLocalRandom.current().nextInt();
        int age2 = ThreadLocalRandom.current().nextInt();
        PatientAssessmentDTO patientAssessmentDTO = new PatientAssessmentDTO("GivenTest","FamilyTypeTest", age,
                familyName);
        PatientAssessmentDTO patientAssessmentDTO2 = new PatientAssessmentDTO("GivenTest2","FamilyTypeTest2", age2,
                familyName);
        List<PatientAssessmentDTO> patientAssessmentDTOLs = new ArrayList<>(Arrays.asList(patientAssessmentDTO, patientAssessmentDTO2));

        Mockito.when(diseaseRiskService.diabetesAssessmentByFamilyName(familyName)).thenReturn(patientAssessmentDTOLs);

        mvc.perform(get("/assess/familyName")
                        .param("familyName", familyName)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$",Matchers.hasSize(2)))
                .andReturn();
    }


}
