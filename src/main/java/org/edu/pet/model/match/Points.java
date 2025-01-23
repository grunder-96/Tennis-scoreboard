package org.edu.pet.model.match;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
public abstract class Points {

    private int current;

    protected Points(int value) {
        checkNegativity(value);
        this.current = value;
    }

    public abstract int next();

    public abstract String view();

    public final int current() {
        return current;
    }

    public void setCurrent(int value) {
        checkNegativity(value);
        this.current = value;
    }

    protected void checkNegativity(int value) {
        if (value < 0) {
            throw new IllegalArgumentException("Value cannot be negative");
        }
    }
}
