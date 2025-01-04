package org.edu.pet.validation.hibernate;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = NotSameValidator.class)
public @interface NotSame {

    String message() default "Player names must not be the same";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    String firstFieldPath();

    String secondFieldPath();
}