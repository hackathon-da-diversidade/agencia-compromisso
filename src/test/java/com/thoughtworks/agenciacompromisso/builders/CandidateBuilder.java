package com.thoughtworks.agenciacompromisso.builders;

import com.thoughtworks.agenciacompromisso.models.Candidate;
import com.thoughtworks.agenciacompromisso.models.Sizes;
import com.thoughtworks.agenciacompromisso.models.enums.GenderExpression;

public class CandidateBuilder {
    private String id = "id";
    private String name = "name";
    private String phoneNumber = "99999999999";
    private GenderExpression genderExpression = GenderExpression.FEMALE;
    private Sizes sizes = new SizesBuilder().build();

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setGenderExpression(GenderExpression genderExpression) {
        this.genderExpression = genderExpression;
    }

    public void setSizes(Sizes sizes) {
        this.sizes = sizes;
    }

    public Candidate build() {
        Candidate candidate = new Candidate();
        candidate.setId(id);
        candidate.setName(name);
        candidate.setPhoneNumber(phoneNumber);
        candidate.setGenderExpression(genderExpression);
        candidate.setSizes(sizes);

        return candidate;
    }
}
