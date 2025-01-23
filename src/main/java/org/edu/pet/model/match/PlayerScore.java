package org.edu.pet.model.match;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PlayerScore {

    private int sets;

    private int games;

    @Builder.Default
    @NonNull
    private Points points = new RegularPoints();
}