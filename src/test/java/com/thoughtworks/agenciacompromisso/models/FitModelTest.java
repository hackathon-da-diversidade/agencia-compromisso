package com.thoughtworks.agenciacompromisso.models;

import com.thoughtworks.agenciacompromisso.models.enums.GenderExpression;
import org.junit.Before;
import org.junit.Test;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.time.Duration;
import java.time.Instant;
import java.util.Date;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class FitModelTest {

    private Validator validator;
    private FitModel fitModel;

    @Before
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
        Date tomorrow = Date.from(Instant.now().plus(Duration.ofDays(1)));
        fitModel.setBirthday(tomorrow);
        assertTrue(isInvalid(fitModel));
    }

    @Test
    public void shouldReturnInvalidWhenFitModelGenderExpressionIsNotSet() {
        fitModel.setGenderExpression(null);
        assertTrue(isInvalid(fitModel));
    }

    @Test
    public void shouldReturnInvalidWhenFitModelSizesAreNotSet() {
        fitModel.setSizes(null);
        assertTrue(isInvalid(fitModel));
    }


    private FitModel getValidFitModel() {
        FitModel fitModel = new FitModel();
        fitModel.setName("João da Silva");
        fitModel.setGenderExpression(GenderExpression.MALE);
        fitModel.setSizes(new Sizes(100.0, 90.0, 120.0, 170.0));
        return fitModel;
    }

    private Boolean isInvalid(FitModel fitModel) {
        Set<ConstraintViolation<FitModel>> violations = validator.validate(fitModel);
        return !violations.isEmpty();
    }
}