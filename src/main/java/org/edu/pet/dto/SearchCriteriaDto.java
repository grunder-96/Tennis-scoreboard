package org.edu.pet.dto;

import lombok.AllArgsConstructor;
import lombok.Value;

@Value
@AllArgsConstructor(staticName = "of")
public class SearchCriteriaDto {

    int pageNumber;
    String namePattern;
}