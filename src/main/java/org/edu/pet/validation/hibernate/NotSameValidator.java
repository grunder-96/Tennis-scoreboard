package org.edu.pet.validation.hibernate;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.lang.reflect.Field;

public class NotSameValidator implements ConstraintValidator<NotSame, Object> {

    private String firstFieldPath;
    private String secondFieldPath;

    @Override
    public void initialize(NotSame constraintAnnotation) {
        firstFieldPath = constraintAnnotation.firstFieldPath();
        secondFieldPath = constraintAnnotation.secondFieldPath();
    }

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {
        Object firstFieldValue;
        Object secondFieldValue;

        try {
            firstFieldValue = getFieldValue(value, firstFieldPath);
            secondFieldValue = getFieldValue(value, secondFieldPath);
        } catch (Exception e) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate("Error while obtaining the field(-s) value(-s).")
                    .addConstraintViolation();
            return false;
        }

        if (!areTypesCompatible(firstFieldValue, secondFieldValue)) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate("Fields have incompatible types")
                    .addConstraintViolation();
            return false;
        }

        if (firstFieldValue instanceof String && secondFieldValue instanceof String) {
            if (((String) firstFieldValue).isBlank() || ((String) secondFieldValue).isBlank()) {
                return true;
            }

            return !((String) firstFieldValue).equalsIgnoreCase((String) secondFieldValue);
        }

        return !firstFieldValue.equals(secondFieldValue);
    }

    private  Object getFieldValue(Object value, String fieldName) throws Exception {
        String[] fields = fieldName.split("\\.");
        Object currentObject = value;

        for (String field : fields) {
            Field currentField = currentObject.getClass().getDeclaredField(field);
            currentField.setAccessible(true);
            currentObject = currentField.get(currentObject);
        }

        return currentObject;
    }

    private boolean areTypesCompatible(Object firstFieldValue, Object secondFieldValue) {
        Class<?> firstFieldClass = firstFieldValue.getClass();
        Class<?> secondFieldClass = secondFieldValue.getClass();

        return firstFieldClass.equals(secondFieldClass);
    }
}