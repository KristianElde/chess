package no.uib.inf101.chess.model;

/**
 * The Option enum represents different options available in the chess
 * application.
 * It includes options such as texture settings, multiplayer mode, color
 * settings, and difficulty level.
 * This enum provides methods to navigate to the next or previous option based
 * on whether AI opposition is enabled.
 */
public enum Option {
    TEXTURE, MULTIPLAYER, COLOR, DIFFICULTY;

    /**
     * Returns the next option based on the current option and AI opposition
     * setting.
     *
     * @param aiOpposition Indicates whether AI opposition is enabled.
     * @return The next option.
     */
    public Option next(boolean aiOpposition) {
        if (aiOpposition) {
            if (this == Option.DIFFICULTY)
                return Option.TEXTURE;
        } else if (this == Option.MULTIPLAYER)
            return Option.TEXTURE;
        return Option.values()[this.ordinal() + 1];
    }

    /**
     * Returns the previous option based on the current option and AI opposition
     * setting.
     *
     * @param aiOpposition Indicates whether AI opposition is enabled.
     * @return The previous option.
     */
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
