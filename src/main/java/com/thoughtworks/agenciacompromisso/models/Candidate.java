package com.thoughtworks.agenciacompromisso.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonView;
import com.thoughtworks.agenciacompromisso.models.enums.Availability;
import com.thoughtworks.agenciacompromisso.models.enums.Education;
import com.thoughtworks.agenciacompromisso.models.enums.GenderExpression;
import org.springframework.data.annotation.Id;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Past;
import javax.validation.constraints.Pattern;
import java.time.LocalDate;

@JsonInclude(Include.NON_NULL)
public class Candidate {
    @Id
    @JsonView(View.List.class)
    private String id;

    @NotEmpty
    @Pattern(regexp = "^[\\p{L} .'-]+$")
    @JsonView(View.List.class)
    private String name;

    @JsonView(View.List.class)
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    @JsonFormat(pattern = "dd/MM/yyyy")
    @Past
    private LocalDate birthday;

    private Availability availability;

    @JsonView(View.List.class)
    @Pattern(regexp = "[(]?[0-9]{0,2}[)]?[0-9( )*]+")
    private String phoneNumber;
    private String projects;
    private String address;

    @JsonView(View.List.class)
    private GenderExpression genderExpression;
    private Education education;
    private String guardianName;
    @Pattern(regexp = "[(]?[0-9]{0,2}[)]?[0-9( )*]+")
    private String guardianPhoneNumber;

    @Valid
    private Sizes sizes;

    @Valid
    private SocialInformation socialInformation;

    private Boolean identifyAsLGBTQIA;
    private String notes;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getBirthday() {
        return birthday;
    }

    public void setBirthday(LocalDate birthday) {
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

    public SocialInformation getSocialInformation() {
        return this.socialInformation;
    }

    public Boolean getIdentifyAsLGBTQIA() {
        return identifyAsLGBTQIA;
    }

    public void setIdentifyAsLGBTQIA(Boolean identifyAsLGBTQIA) {
        this.identifyAsLGBTQIA = identifyAsLGBTQIA;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }
}
