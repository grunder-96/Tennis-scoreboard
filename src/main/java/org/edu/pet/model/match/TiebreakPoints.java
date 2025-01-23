package org.edu.pet.model.match;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class TiebreakPoints extends Points {

    @Override
    public int next() {
        return current() + 1;
    }

    @Override
    public String view() {
        return Integer.toString(current());
    }

    public TiebreakPoints(int value) {
        super(value);
    }
}