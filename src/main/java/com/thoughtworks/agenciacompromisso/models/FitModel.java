package com.thoughtworks.agenciacompromisso.models;

import com.thoughtworks.agenciacompromisso.models.enums.Availability;
import com.thoughtworks.agenciacompromisso.models.enums.Education;
import com.thoughtworks.agenciacompromisso.models.enums.GenderExpression;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.Valid;
import javax.validation.constraints.*;
import java.util.Date;

@Document(collation = "fitmodels")
public class FitModel {

    @Id
    private ObjectId id;

    @NotEmpty
    @Pattern(regexp = "^[\\p{L} .'-]+$")
    private String name;
    @Past
    private Date birthday;
    private Availability availability;
    @Pattern(regexp = "\\d{8,12}")
    private String phoneNumber;
    private String projects;
    private String address;
    @NotNull
    private GenderExpression genderExpression;
    private Education education;
    private String guardianName;
    private String guardianPhoneNumber;

    @NotNull
    @Valid
    private Sizes sizes;

    @Valid
    private SocialInformation socialInformation;

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public Availability getAvailability() {
        return availability;
    }

    public void setAvailability(Availability availability) {
        this.availability = availability;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getProjects() {
        return projects;
    }

    public void setProjects(String projects) {
        this.projects = projects;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public GenderExpression getGenderExpression() {
        return genderExpression;
    }

    public void setGenderExpression(GenderExpression genderExpression) {
        this.genderExpression = genderExpression;
    }

    public Education getEducation() {
        return education;
    }

    public void setEducation(Education education) {
        this.education = education;
    }

    public String getGuardianName() {
        return guardianName;
    }

    public void setGuardianName(String guardianName) {
        this.guardianName = guardianName;
    }

    public String getGuardianPhoneNumber() {
        return guardianPhoneNumber;
    }

    public void setGuardianPhoneNumber(String guardianPhoneNumber) {
        this.guardianPhoneNumber = guardianPhoneNumber;
    }

    public Sizes getSizes() {
        return sizes;
    }

    public void setSizes(Sizes sizes) {
        this.sizes = sizes;
    }

    public void setSocialInformation(SocialInformation socialInformation) {
        this.socialInformation = socialInformation;
    }
}
