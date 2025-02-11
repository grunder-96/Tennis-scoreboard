package org.edu.pet.dto;

import lombok.AllArgsConstructor;
import lombok.Value;

import java.util.List;

@Value
@AllArgsConstructor(staticName = "of")
public class PageDto {

    List<MatchResponseDto> matches;
    long currentPage;
    long totalPages;
    String namePattern;
}