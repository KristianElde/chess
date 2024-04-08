package no.uib.inf101.chess;

import javax.swing.JFrame;

import no.uib.inf101.chess.model.ChessModel;
import no.uib.inf101.chess.view.ChessView;

/**
 * Hello world!
 */
public class Main {

  public static final String WINDOW_TITLE = "INF101 Chess - Kristian Elde Johansen";

  public static void main(String[] args) {
    ChessModel model = new ChessModel();
    ChessView view = new ChessView(model);

    JFrame frame = new JFrame(WINDOW_TITLE);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setContentPane(view);
    frame.pack();
    frame.setVisible(true);
    frame.setResizable(false);
  }
}
