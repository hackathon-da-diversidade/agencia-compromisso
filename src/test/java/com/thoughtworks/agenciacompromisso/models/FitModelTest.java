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

public class FitModelTest {

    private Validator validator;
    private FitModel fitModel;

    @BeforeEach
    public void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
        fitModel = getValidFitModel();
    }

    @Test
    public void shouldNotBeInvalidWhenEverythingIsSet() {
        assertFalse(isInvalid(fitModel));
    }

    @Test
    public void shouldReturnInvalidWhenFitModelNameContainsNumbers() {
        fitModel.setName("João 123 da Silva");
        assertTrue(isInvalid(fitModel));
    }

    @Test
    public void shouldReturnInvalidWhenFitModelBirthdayIsNotAPastDate() {
        LocalDate tomorrow = LocalDate.now().plus(1, ChronoUnit.DAYS);
        fitModel.setBirthday(tomorrow);
        assertTrue(isInvalid(fitModel));
    }

    @Test
    public void shouldReturnValidWhenFitModelGenderExpressionIsNotSet() {
        fitModel.setGenderExpression(null);
        assertFalse(isInvalid(fitModel));
    }

    @Test
    public void shouldReturnValidWhenFitModelSizesAreNotSet() {
        fitModel.setSizes(null);
        assertFalse(isInvalid(fitModel));
    }

    @Test
    public void shouldReturnNullOnLGBTQIAFieldIfNotSet() throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();

        String objAsStr = mapper.writeValueAsString(fitModel);

        System.out.println(objAsStr);

        assertThat(objAsStr, containsString("João da Silva"));
        assertThat(objAsStr, not(containsString("LGBTQIA+")));
    }


    private FitModel getValidFitModel() {
        FitModel fitModel = new FitModel();
        fitModel.setName("João da Silva");
        fitModel.setGenderExpression(GenderExpression.MALE);
        fitModel.setSizes(new Sizes(100.0, 90.0, 120.0, 170.0, "M", 42, 40));
        fitModel.setBirthday(LocalDate.of(1990, 12, 14));
        fitModel.setNotes("obs");
        fitModel.setAvailability(Availability.MORNING);
        return fitModel;
    }


    private Boolean isInvalid(FitModel fitModel) {
        Set<ConstraintViolation<FitModel>> violations = validator.validate(fitModel);
        return !violations.isEmpty();
    }
}