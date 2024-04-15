package no.uib.inf101.chess.view.design;

import java.awt.Font;

public class DefaultFontTheme implements FontTheme {

    @Override
    public Font getDefaultFont() {
        return new Font("Calibri", Font.BOLD, 16);
    }

    @Override
    public Font getGameOverScreenFont() {
        return new Font("Calibri", Font.BOLD, 24);
    }

}
