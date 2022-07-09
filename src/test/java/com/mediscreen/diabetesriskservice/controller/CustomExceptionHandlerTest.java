package com.mediscreen.diabetesriskservice.controller;

import com.mediscreen.diabetesriskservice.DiabetesRiskServiceApplication;
import com.mediscreen.diabetesriskservice.customExceptions.FamilyNameNotFoundException;
import com.mediscreen.diabetesriskservice.customExceptions.PatIdNotFoundException;
import com.mediscreen.diabetesriskservice.services.DiseaseRiskService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(CustomExceptionHandler.class)
@ContextConfiguration(classes ={DiabetesRiskServiceApplication.class})
public class CustomExceptionHandlerTest {

    @Autowired
    MockMvc mvc;

    @MockBean
    DiseaseRiskService diseaseRiskService;

    @Test
    public void handlePatientIdNotFoundException () throws Exception {

        long id = 1L;

        given(diseaseRiskService.diabetesAssessmentById(any(Long.class))).willThrow(PatIdNotFoundException.class);

        mvc.perform(get("/assess/id")
                        .param("id",Long.toString(id))
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }
    @Test
    public void handleFamilyNameNotFoundException() throws Exception {

        long id = 1L;
        given(diseaseRiskService.diabetesAssessmentByFamilyName(any(String.class))).willThrow(FamilyNameNotFoundException.class);

        mvc.perform(get("/assess/familyName")
                        .param("id",Long.toString(id))
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }
}
