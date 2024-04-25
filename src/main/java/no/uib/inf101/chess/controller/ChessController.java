package no.uib.inf101.chess.controller;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import no.uib.inf101.chess.Main;
import no.uib.inf101.chess.model.ChessModel;
import no.uib.inf101.chess.model.Column;
import no.uib.inf101.chess.model.GameState;
import no.uib.inf101.chess.model.Move;
import no.uib.inf101.chess.model.Square;
import no.uib.inf101.chess.view.ChessView;

/**
 * The ChessController class is responsible for handling mouse events in the
 * chess game. It implements the MouseListener interface to listen for mouse
 * events.
 */
public class ChessController implements MouseListener {

    private ControllableModel model;
    private ChessView view;

    /**
     * Constructs a new ChessController with the specified model and view.
     *
     * @param model The controllable model representing the game state and options.
     * @param view  The view representing the chess game screen.
     */
    public ChessController(ChessModel model, ChessView view) {
        this.model = model;
        this.view = view;

        this.view.addMouseListener(this);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (model.getGameState() == GameState.ACTIVE) {
            Square selected = pixelToSquareConverter(e.getX(), e.getY());
            model.setSelectedSquare(selected);

            view.repaint();

            if (model.isAiOpposition() && model.getBoard().getToDraw() != model.getPlayerColor()) {
                Move selectedMove = model.getAiPlayer().getBestMove();
                model.setSelectedSquare(selectedMove.from());
                model.setSelectedSquare(selectedMove.to());

                view.repaint();
            }
        }

        else if (model.getGameState() == GameState.CHECKMATE || model.getGameState() == GameState.STALEMATE) {
            rerunApp();
        }
    }

    @Override
    public void mouseEntered(MouseEvent arg0) {
        /* ignore */
    }

    @Override
    public void mouseExited(MouseEvent arg0) {
        /* ignore */
    }

    @Override
    public void mousePressed(MouseEvent arg0) {
        /* ignore */
    }

    @Override
    public void mouseReleased(MouseEvent arg0) {
        /* ignore */
    }

    private Square pixelToSquareConverter(int clickX, int clickY) {
        double boardWidth = view.getBounds().width - ChessView.HORIZONTAL_OUTERMARGIN * 2;
        double boardHeight = view.getBounds().height - ChessView.VERTICAL_OUTERMARGIN * 2;
        double fieldWidth = boardWidth / 8;
        double fieldHeight = boardHeight / 8;

        int colOrdinal = 0;
        while ((colOrdinal + 1) * fieldWidth + ChessView.HORIZONTAL_OUTERMARGIN < clickX) {
            colOrdinal += 1;
        }
        Column[] cols = Column.values();
        Column col = cols[colOrdinal];

        int row = 1;
        while (view.getBounds().height - (row * fieldHeight + ChessView.VERTICAL_OUTERMARGIN) > clickY) {
            row += 1;
        }

        return model.getBoard().get(col, row);
    }

    private void rerunApp() {
        Main.main(new String[0]);
    }

}
