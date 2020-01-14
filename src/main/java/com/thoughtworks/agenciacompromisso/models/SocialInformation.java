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

    public Ethnicity getEthnicity() {
        return ethnicity;
    }

    public void setEthnicity(Ethnicity ethnicity) {
        this.ethnicity = ethnicity;
    }

    public Housing getHousing() {
        return housing;
    }

    public void setHousing(Housing housing) {
        this.housing = housing;
    }

    public int getNumberOfResidents() {
        return numberOfResidents;
    }

    public void setNumberOfResidents(int numberOfResidents) {
        this.numberOfResidents = numberOfResidents;
    }

    public String getOccupation() {
        return occupation;
    }

    public void setOccupation(String occupation) {
        this.occupation = occupation;
    }

    public OccupationMode getOccupationMode() {
        return occupationMode;
    }

    public void setOccupationMode(OccupationMode occupationMode) {
        this.occupationMode = occupationMode;
    }

    public FamilyIncome getFamilyIncome() {
        return familyIncome;
    }

    public void setFamilyIncome(FamilyIncome familyIncome) {
        this.familyIncome = familyIncome;
    }

    public int getNumberOfChildren() {
        return numberOfChildren;
    }

    public void setNumberOfChildren(int numberOfChildren) {
        this.numberOfChildren = numberOfChildren;
    }

}
