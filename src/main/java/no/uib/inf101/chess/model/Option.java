package no.uib.inf101.chess.model;

public enum Option {
    TEXTURE, MULTIPLAYER, COLOR, DIFFICULTY;

    public Option next(boolean aiOpposition) {
        if (aiOpposition) {
            if (this == Option.DIFFICULTY)
                return Option.TEXTURE;
        } else if (this == Option.MULTIPLAYER)
            return Option.TEXTURE;
        return Option.values()[this.ordinal() + 1];
    }

    public Option prev(boolean aiOpposition) {
        if (this == Option.TEXTURE) {
            if (aiOpposition)
                return Option.DIFFICULTY;
            else
                return Option.MULTIPLAYER;
        }
        return Option.values()[this.ordinal() - 1];
    }
}
