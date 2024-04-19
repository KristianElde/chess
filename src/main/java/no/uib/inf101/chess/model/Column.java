package no.uib.inf101.chess.model;

public enum Column {
    A, B, C, D, E, F, G, H;

    public Column nextCol() {
        int nextOrdinal = this.ordinal() + 1;
        if (nextOrdinal > 7) {
            return null;
        }
        return Column.values()[nextOrdinal];
    }

    public Column nextNextCol() {
        int nextOrdinal = this.ordinal() + 2;
        if (nextOrdinal > 7) {
            return null;
        }
        return Column.values()[nextOrdinal];
    }

    public Column prevCol() {
        int prevOrdinal = this.ordinal() - 1;
        if (prevOrdinal < 0) {
            return null;
        }
        return Column.values()[prevOrdinal];
    }

    public Column prevPrevCol() {
        int prevOrdinal = this.ordinal() - 2;
        if (prevOrdinal < 0) {
            return null;
        }
        return Column.values()[prevOrdinal];
    }
}
