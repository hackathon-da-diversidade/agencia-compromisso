package com.thoughtworks.agenciacompromisso.models;

import com.thoughtworks.agenciacompromisso.models.enums.Ethnicity;
import com.thoughtworks.agenciacompromisso.models.enums.FamilyIncome;
import com.thoughtworks.agenciacompromisso.models.enums.Housing;
import com.thoughtworks.agenciacompromisso.models.enums.OccupationMode;

import javax.validation.constraints.Min;

public class SocialInformation {
    private Ethnicity ethnicity;
    private Housing housing;
    @Min(1)
    private int numberOfResidents;
    private String occupation;
    private OccupationMode occupationMode;
    private FamilyIncome familyIncome;
    @Min(0)
    private int numberOfChildren;
    private boolean identifyAsLGBTQIA;
}
