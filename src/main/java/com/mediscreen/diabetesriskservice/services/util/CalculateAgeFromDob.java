package com.mediscreen.diabetesriskservice.services.util;

import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

@Component
public class CalculateAgeFromDob {
    /**
     *
     * @param dob
     * @return LocalDate the dob (date of birth) converted in age (At Local zoneId)
     */
    public int calculateAge (Date dob){
        Date currentDate = new Date(System.currentTimeMillis());
        LocalDate currentDateForConvertion = currentDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        LocalDate dobForConvertion = dob.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

        return currentDateForConvertion.compareTo(dobForConvertion);
    }


}
