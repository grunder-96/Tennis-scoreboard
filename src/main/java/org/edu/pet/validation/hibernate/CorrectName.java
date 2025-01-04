package org.edu.pet.validation.hibernate;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = CorrectNameValidator.class)
public @interface CorrectName {

    String message() default "Player name must be specified in the format \"Name Surname\"(en)";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}