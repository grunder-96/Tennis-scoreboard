package org.edu.pet.validation;

import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import java.util.Arrays;
import java.util.stream.Collectors;

@Getter
@RequiredArgsConstructor
public final class NameFormatter {

    private boolean isSpacesRemove;
    private boolean isCapitalizeFirstLetter;

    @NonNull
    private String name;

    public NameFormatter removeExtraSpaces() {
        if (!isSpacesRemove && !name.isBlank()) {
            name = name.strip().replaceAll("\\s+", " ");
            isSpacesRemove = true;
        }

        return this;
    }

    public NameFormatter capitalizeFirstLetter() {

        if (isCapitalizeFirstLetter || name.isBlank()) {
            return this;
        }

        if (!isSpacesRemove) {
            removeExtraSpaces();
        }

        name = Arrays.stream(name.split(" "))
                .map(word -> Character.toUpperCase(word.charAt(0)) + word.substring(1))
                .collect(Collectors.joining(" "));

        isCapitalizeFirstLetter = true;

        return this;
    }
}