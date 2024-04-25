package no.uib.inf101.chess;

import javax.swing.JFrame;

import no.uib.inf101.chess.controller.ChessController;
import no.uib.inf101.chess.controller.MainMenuController;
import no.uib.inf101.chess.model.ChessModel;
import no.uib.inf101.chess.view.ChessView;
import no.uib.inf101.chess.view.design.ColorTheme;
import no.uib.inf101.chess.view.design.DefaultColorTheme;
import no.uib.inf101.chess.view.design.DefaultFontTheme;
import no.uib.inf101.chess.view.design.FontTheme;
import no.uib.inf101.chess.view.design.StarWarsTextureTheme;
import no.uib.inf101.chess.view.design.TextureTheme;

/**
 * The Main class contains the entry point for the INF101 Chess application. It
 * sets up the chess model, views, controllers, and the main application window.
 */
public class Main {

  public static final String WINDOW_TITLE = "INF101 Chess - Kristian Elde Johansen";

  /**
   * The main method initializes the chess model, color theme, texture theme, font
   * theme, and sets up the main application window with the chess view and
   * controllers.
   * 
   * @param args The command line arguments (not used).
   */
  public static void main(String[] args) {
    // Initialize the chess model
    ChessModel model = new ChessModel();

    // Initialize the color theme, texture theme, and font theme
    ColorTheme colorTheme = new DefaultColorTheme();
    TextureTheme textureTheme = new StarWarsTextureTheme();
    FontTheme fontTheme = new DefaultFontTheme();

    // Create the chess view with the initialized model and themes
    ChessView view = new ChessView(model, colorTheme, textureTheme, fontTheme);

    // Create and attach controllers to the view
    new ChessController(model, view);
    new MainMenuController(model, view);

    // Set up the main application window
    JFrame frame = new JFrame(WINDOW_TITLE);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setContentPane(view);
    frame.pack();
    frame.setVisible(true);
    frame.setResizable(false);
  }
}
