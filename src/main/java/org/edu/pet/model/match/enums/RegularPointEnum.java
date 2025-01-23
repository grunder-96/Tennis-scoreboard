package org.edu.pet.model.match.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Arrays;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Getter
public enum RegularPointEnum {
    lOVE("0", 0),
    FIFTEEN("15", 1),
    THIRTY("30", 2),
    FORTY("40", 3),
    ADVANTAGE("AD", 4),
    WON("WON", 5);

    private static final Map<Integer, RegularPointEnum> POINT_INDEX_ENUM_MAP;

    static {
        POINT_INDEX_ENUM_MAP = Arrays.stream(RegularPointEnum.values())
                .collect(Collectors.toMap(RegularPointEnum::getPointValue, Function.identity()));
    }

    private final String view;
    private final int pointValue;

    public RegularPointEnum getNext() {
        return this == WON ? this : POINT_INDEX_ENUM_MAP.get(this.getPointValue() + 1);
    }

    public static RegularPointEnum findByPointValue(int pointValue) {
        return POINT_INDEX_ENUM_MAP.get(pointValue);
    }
}