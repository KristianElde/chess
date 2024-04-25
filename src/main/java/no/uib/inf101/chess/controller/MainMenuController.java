package no.uib.inf101.chess.controller;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import no.uib.inf101.chess.model.GameState;
import no.uib.inf101.chess.model.Option;
import no.uib.inf101.chess.view.ChessView;

public class MainMenuController implements KeyListener {

    private ControllableModel model;
    private ChessView view;

    public MainMenuController(ControllableModel model, ChessView view) {
        this.model = model;
        this.view = view;

        this.view.setFocusable(true);
        this.view.addKeyListener(this);
    }

    @Override
    public void keyTyped(KeyEvent e) {
        /* ignore */
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (model.getGameState() == GameState.MAIN_MENU) {
            if (e.getKeyCode() == KeyEvent.VK_SPACE) {
                model.setGameState(GameState.ACTIVE);
            }
            if (e.getKeyCode() == KeyEvent.VK_DOWN) {
                model.setSelectedOption(model.getSelectedOption().next(model.isAiOpposition()));
            }
            if (e.getKeyCode() == KeyEvent.VK_UP) {
                model.setSelectedOption(model.getSelectedOption().prev(model.isAiOpposition()));
            }
            if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
                if (model.getSelectedOption() == Option.TEXTURE)
                    view.toggleTextureTheme();
                else
                    model.toggleSelectedOption(true);
            }
            if (e.getKeyCode() == KeyEvent.VK_LEFT) {
                if (model.getSelectedOption() == Option.TEXTURE)
                    view.toggleTextureTheme();
                else
                    model.toggleSelectedOption(false);
            }
        }

        view.repaint();
    }

    @Override
    public void keyReleased(KeyEvent e) {
        /* ignore */
    }

}
