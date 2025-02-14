package org.edu.pet.validation.hibernate;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CorrectNameValidator implements ConstraintValidator<CorrectName, String> {

    private static final String CORRECT_NAME_REGEX = "^[a-zA-Z]+['-]?([(a-zA-Z]{2,})? [a-zA-Z]+['-]?([(a-zA-Z]{2,})?$";

    @Override
    public void initialize(CorrectName constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        Pattern pattern = Pattern.compile(CORRECT_NAME_REGEX);
        Matcher matcher = pattern.matcher(value);
        return matcher.matches();
    }
}