package no.uib.inf101.chess;

import javax.swing.JFrame;

import no.uib.inf101.chess.controller.ChessController;
import no.uib.inf101.chess.model.ChessModel;
import no.uib.inf101.chess.view.ChessView;
import no.uib.inf101.chess.view.design.ColorTheme;
import no.uib.inf101.chess.view.design.DefaultColorTheme;
import no.uib.inf101.chess.view.design.DefaultFontTheme;
import no.uib.inf101.chess.view.design.DefaultTextureTheme;
import no.uib.inf101.chess.view.design.FontTheme;
import no.uib.inf101.chess.view.design.TextureTheme;

/**
 * Hello world!
 */
public class Main {

  public static final String WINDOW_TITLE = "INF101 Chess - Kristian Elde Johansen";

  public static void main(String[] args) {
    ChessModel model = new ChessModel();
    ColorTheme colorTheme = new DefaultColorTheme();
    TextureTheme textureTheme = new DefaultTextureTheme();
    FontTheme fontTheme = new DefaultFontTheme();
    ChessView view = new ChessView(model, colorTheme, textureTheme, fontTheme);

    new ChessController(model, view);

    JFrame frame = new JFrame(WINDOW_TITLE);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setContentPane(view);
    frame.pack();
    frame.setVisible(true);
    frame.setResizable(false);
  }
}
