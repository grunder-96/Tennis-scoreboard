package org.edu.pet.dto.match;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum Points {

     lOVE(0),
     FIFTEEN(15),
     THIRTY(30),
     FORTY(40);

     @Getter
     private final int value;
}