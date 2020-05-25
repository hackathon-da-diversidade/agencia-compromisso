package com.thoughtworks.agenciacompromisso.models;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.thoughtworks.agenciacompromisso.models.enums.Availability;
import com.thoughtworks.agenciacompromisso.models.enums.GenderExpression;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Set;

import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.Matchers.containsString;
import static org.junit.Assert.assertThat;
import static org.junit.jupiter.api.Assertions.*;

public class CandidateTest {

    private Validator validator;
    private Candidate candidate;

    @BeforeEach
    public void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
        candidate = getValidCandidate();
    }

    @Test
    public void shouldNotBeInvalidWhenEverythingIsSet() {
        assertFalse(isInvalid(candidate));
    }

    @Test
    public void shouldReturnInvalidWhenCandidateNameContainsNumbers() {
        candidate.setName("João 123 da Silva");
        assertTrue(isInvalid(candidate));
    }

    @Test
    public void shouldReturnInvalidWhenCandidateBirthdayIsNotAPastDate() {
        LocalDate tomorrow = LocalDate.now().plus(1, ChronoUnit.DAYS);
        candidate.setBirthday(tomorrow);
        assertTrue(isInvalid(candidate));
    }

    @Test
    public void shouldReturnValidWhenCandidateGenderExpressionIsNotSet() {
        candidate.setGenderExpression(null);
        assertFalse(isInvalid(candidate));
    }

    @Test
    public void shouldReturnValidWhenCandidateSizesAreNotSet() {
        candidate.setSizes(null);
        assertFalse(isInvalid(candidate));
    }

    @Test
    public void shouldReturnNullOnLGBTQIAFieldIfNotSet() throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();

        String objAsStr = mapper.writeValueAsString(candidate);

        assertThat(objAsStr, containsString("João da Silva"));
        assertThat(objAsStr, not(containsString("identifyAsLGBTQIA")));
    }

    @Test
    public void shouldSetLGBTQIAFieldAsTrue() throws JsonProcessingException {
        candidate.setIdentifyAsLGBTQIA(true);
        ObjectMapper mapper = new ObjectMapper();
        String objAsStr = mapper.writeValueAsString(candidate);

        assertThat(objAsStr, containsString("\"identifyAsLGBTQIA\":true"));

    }

    @Test
    public void shouldSetLGBTQIAFieldAsFalse() throws JsonProcessingException {
        candidate.setIdentifyAsLGBTQIA(false);
        ObjectMapper mapper = new ObjectMapper();
        String objAsStr = mapper.writeValueAsString(candidate);

        assertThat(objAsStr, containsString("\"identifyAsLGBTQIA\":false"));

    }


    private Candidate getValidCandidate() {
        Candidate candidate = new Candidate();
        candidate.setName("João da Silva");
        candidate.setGenderExpression(GenderExpression.MALE);
        candidate.setSizes(new Sizes(100.0, 90.0, 120.0, 170.0, "M", 42, 40));
        candidate.setBirthday(LocalDate.of(1990, 12, 14));
        candidate.setNotes("obs");
        candidate.setAvailability(Availability.MORNING);
        return candidate;
    }


    private Boolean isInvalid(Candidate candidate) {
        Set<ConstraintViolation<Candidate>> violations = validator.validate(candidate);
        return !violations.isEmpty();
    }
}