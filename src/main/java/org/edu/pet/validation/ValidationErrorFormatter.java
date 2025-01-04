package org.edu.pet.validation;

import jakarta.validation.ConstraintViolation;
import lombok.experimental.UtilityClass;

import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@UtilityClass
public class ValidationErrorFormatter {

    public <T> Map<String, Map<String, String>> format(Set<ConstraintViolation<T>> violations) {
        return violations.stream()
            .collect(Collectors.groupingBy(
                violation -> violation.getPropertyPath().toString(),
                Collectors.toMap(
                    violation -> violation.getConstraintDescriptor().getAnnotation().annotationType().getSimpleName(),
                    ConstraintViolation::getMessage,
                    (s1, s2) -> String.join(", ", s1, s2)
                )
            ));
    }
}