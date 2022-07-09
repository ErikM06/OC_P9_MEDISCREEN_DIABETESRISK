package com.mediscreen.diabetesriskservice.services;


import com.mediscreen.diabetesriskservice.model.PatientHist;
import com.mediscreen.diabetesriskservice.proxy.PatientNoteClientProxy;
import com.mediscreen.diabetesriskservice.services.util.PatientNoteContentReader;
import com.mediscreen.diabetesriskservice.services.util.WordsTrigger;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CalculateTriggerService {

    Logger logger = LoggerFactory.getLogger(CalculateTriggerService.class);
    @Autowired
    PatientNoteClientProxy patientNoteClientProxy;

    @Autowired
    PatientNoteContentReader patientNoteContentReader;

    /**
     *
     * @param id
     * @return int the size of trigger list.
     * Looping on patientNote content and match WordsTriggers with converted String from content
     */
    public Integer getTriggerCount (Long id){
        List<String> triggerCountLs = new ArrayList<>();
        List<PatientHist> patientNoteLs;
        patientNoteLs = patientNoteClientProxy.getPatientHistByPatId(id);

        List<String> triggers = WordsTrigger.listOfWordTriggers;

        patientNoteLs.forEach (patientNote -> {
            List<String> contentWords = patientNoteContentReader.ToStringListConvertor(patientNote.getContent());

            contentWords.forEach(cw -> {
                   triggerCountLs.addAll(triggers.stream().filter(t-> t.equals(cw)).collect(Collectors.toList()));

            });
        });
        logger.info("in getTriggerCount "+ triggerCountLs.size());
        return triggerCountLs.size();
    }


}
