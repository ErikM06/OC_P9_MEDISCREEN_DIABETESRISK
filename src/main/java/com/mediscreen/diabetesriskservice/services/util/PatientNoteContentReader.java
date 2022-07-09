package com.mediscreen.diabetesriskservice.services.util;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;


@Component
public class PatientNoteContentReader {

    Logger logger = LoggerFactory.getLogger(PatientNoteContentReader.class);

    /**
     *
     * @param patHistoryContent
     * @return a List of String formatted without dot and comma
     */
    public List<String> ToStringListConvertor (String patHistoryContent){
        List<String> contentToList;

        String formatContentWithoutComma = patHistoryContent.replace(",","");
        String formatContentWithoutDot = formatContentWithoutComma.replace(".", "");
        String formatContentToLowerCase = formatContentWithoutDot.toLowerCase(Locale.ROOT);

        // all words to an array
        String[] split = StringUtils.split(formatContentToLowerCase);
        contentToList = List.of(split);


        // log every word triggered in content
       // contentToList.forEach(w -> logger.info("in PatientHistContentReader "+ w));


        return contentToList;
    }
}
