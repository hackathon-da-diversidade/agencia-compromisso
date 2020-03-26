package com.thoughtworks.agenciacompromisso.models;

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

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

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


    private FitModel getValidFitModel() {
        FitModel fitModel = new FitModel();
        fitModel.setName("João da Silva");
        fitModel.setGenderExpression(GenderExpression.MALE);
        fitModel.setSizes(new Sizes(100.0, 90.0, 120.0, 170.0,  "M", 42));
        fitModel.setBirthday(LocalDate.of(1990, 12, 14));
        fitModel.setAvailability(Availability.MORNING);
        fitModel.setNotes("obs");
        return fitModel;
    }


    private Boolean isInvalid(FitModel fitModel) {
        Set<ConstraintViolation<FitModel>> violations = validator.validate(fitModel);
        return !violations.isEmpty();
    }
}