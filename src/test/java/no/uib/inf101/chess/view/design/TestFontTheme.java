package no.uib.inf101.chess.view.design;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.awt.Font;

import org.junit.jupiter.api.Test;

public class TestFontTheme {

    @Test
    void sanityDefaultFontThemeTest() {
        FontTheme fonts = new DefaultFontTheme();
        assertEquals(new Font("Calibri", Font.BOLD, 16), fonts.getDefaultFont());
        assertEquals(new Font("Calibri", Font.BOLD, 24), fonts.getGameOverScreenFont());
    }
}
