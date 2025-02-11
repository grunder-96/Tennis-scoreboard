package org.edu.pet.service;

import org.edu.pet.dto.MatchResponseDto;
import org.edu.pet.dto.PageDto;
import org.edu.pet.dto.SearchCriteriaDto;
import org.edu.pet.mapper.MatchMapper;
import org.edu.pet.model.entity.Match;
import org.edu.pet.model.match.MatchScore;
import org.edu.pet.repository.MatchRepository;
import org.edu.pet.service.condition.CriteriaCondition;
import org.edu.pet.service.condition.MatchCriteriaConditions;

import java.util.List;

import static org.edu.pet.util.PaginationUtil.*;

public class FinishedMatchesPersistenceService {

    private final MatchRepository matchRepository = MatchRepository.getInstance();
    private final MatchMapper mapper = MatchMapper.INSTANCE;

    public void saveMatch(MatchScore matchScore) {
        Match match = mapper.fromMatchScore(matchScore);
        matchRepository.save(match);
    }

    public PageDto findMatchesBy(SearchCriteriaDto searchCriteriaDto) {

        int pageNumber = searchCriteriaDto.getPageNumber();
        ensureGreaterZero(pageNumber);

        CriteriaCondition<Match> criteriaCondition;
        String namePattern = searchCriteriaDto.getNamePattern();

        criteriaCondition = CriteriaCondition.
            where(MatchCriteriaConditions.hasParticipantWithNamePattern(namePattern));

        long totalRecords = matchRepository.countBy(criteriaCondition);
        int totalPages = calculateTotalPages(totalRecords);
        ensureLessOrEqualsTotal(pageNumber, totalPages);
        int offset = calculateOffset(pageNumber);

        List<Match> matches = matchRepository.findBy(criteriaCondition, offset, DEFAULT_PAGE_SIZE);
        List<MatchResponseDto> matchesDto = mapper.fromMatches(matches);
        return PageDto.of(matchesDto, pageNumber, totalPages, namePattern);
    }

    private void ensureGreaterZero(int pageNumber) {
        if (pageNumber < 1) {
            throw new IllegalArgumentException("Page number must be greater than zero");
        }
    }

    private void ensureLessOrEqualsTotal(int pageNumber, long totalPages) {
        if (pageNumber > totalPages) {
            throw new IllegalArgumentException("Page number exceeds the valid range");
        }
    }

    private int calculateTotalPages(long totalRecords) {
        return  totalRecords == 0 ?
                1 : (int) Math.ceil((double) totalRecords / DEFAULT_PAGE_SIZE);
    }

    private int calculateOffset(int pageNumber) {
        return (pageNumber - 1) * DEFAULT_PAGE_SIZE;
    }
}