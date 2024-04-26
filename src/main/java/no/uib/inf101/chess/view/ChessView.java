package no.uib.inf101.chess.view;

import javax.swing.JPanel;

import no.uib.inf101.chess.model.ChessBoard;
import no.uib.inf101.chess.model.ChessColor;
import no.uib.inf101.chess.model.ChessModel;
import no.uib.inf101.chess.model.Column;
import no.uib.inf101.chess.model.GameState;
import no.uib.inf101.chess.model.Option;
import no.uib.inf101.chess.model.Square;
import no.uib.inf101.chess.model.aiPlayer.AggressiveAI;
import no.uib.inf101.chess.model.aiPlayer.CarefulAI;
import no.uib.inf101.chess.model.aiPlayer.RandomAI;
import no.uib.inf101.chess.model.pieces.Piece;
import no.uib.inf101.chess.view.design.ColorTheme;
import no.uib.inf101.chess.view.design.DefaultTextureTheme;
import no.uib.inf101.chess.view.design.FontTheme;
import no.uib.inf101.chess.view.design.StarWarsTextureTheme;
import no.uib.inf101.chess.view.design.TextureTheme;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

/**
 * The ChessView class represents the graphical view of the chess game. It
 * extends JPanel and provides methods for drawing the chessboard, pieces, and
 * various game elements based on the provided model, color theme, texture
 * theme, and font theme.
 */
public class ChessView extends JPanel {

    private ViewableModel model;
    private ColorTheme colorTheme;
    private TextureTheme textureTheme;
    private FontTheme fontTheme;

    double boardWidth;
    double boardHeight;
    double squareWidth;
    double squareHeight;

    // Constants for margins, cell size, and cell margin
    public static final double VERTICAL_OUTERMARGIN = 50;
    public static final double HORIZONTAL_OUTERMARGIN = 50;
    private static final double CELL_SIZE = 80;
    private static final double CELL_MARGIN = 1;

    /**
     * Constructs a ChessView object with the specified model, color theme, texture
     * theme, and font theme.
     * 
     * @param model        The chess model to be displayed.
     * @param colorTheme   The color theme used for the graphical elements.
     * @param textureTheme The texture theme used for the chess pieces.
     * @param fontTheme    The font theme used for text rendering.
     */
    public ChessView(ChessModel model, ColorTheme colorTheme, TextureTheme textureTheme, FontTheme fontTheme) {
        double windowWidth = (CELL_SIZE + CELL_MARGIN) * model.getBoard().cols() + CELL_MARGIN
                + HORIZONTAL_OUTERMARGIN;
        double windowHeight = (CELL_SIZE + CELL_MARGIN) * model.getBoard().rows() + CELL_MARGIN
                + VERTICAL_OUTERMARGIN;
        this.boardWidth = windowWidth - 2 * HORIZONTAL_OUTERMARGIN;
        this.boardHeight = windowHeight - 2 * VERTICAL_OUTERMARGIN;
        this.squareWidth = boardWidth / 8;
        this.squareHeight = boardHeight / 8;

        this.setFocusable(true);
        this.setPreferredSize(new Dimension((int) windowWidth, (int) windowHeight));
        this.model = model;
        this.colorTheme = colorTheme;
        this.textureTheme = textureTheme;
        this.fontTheme = fontTheme;
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;

        if (model.getGameState() == GameState.MAIN_MENU) {
            this.setBackground(colorTheme.getBackgroundColorMenu());
            drawMainMenu(g2);
        } else {
            this.setBackground(colorTheme.getBackgroundColorActive());
            drawGame(g2);
        }
    }

    /**
     * Toggles the texture theme between the default theme and the Star Wars theme.
     */
    public void toggleTextureTheme() {
        if (textureTheme instanceof DefaultTextureTheme)
            textureTheme = new StarWarsTextureTheme();
        else if (textureTheme instanceof StarWarsTextureTheme)
            textureTheme = new DefaultTextureTheme();
    }

    private void drawMainMenu(Graphics2D g) {
        g.setColor(colorTheme.getBackgroundColorActive());
        g.setFont(fontTheme.getHeaderFont());
        Inf101Graphics.drawCenteredString(g, "INF101: CHESS", getWidth() / 2, getHeight() / 7);
        Inf101Graphics.drawCenteredString(g, "Made by: Kristian Elde Johansen", getWidth() / 2, getHeight() / 5);
        g.setFont(fontTheme.getDefaultFont());
        Inf101Graphics.drawCenteredString(g, "Use arrow keys to select your preferred options", getWidth() / 2,
                getHeight() / 3.2);
        Inf101Graphics.drawCenteredString(g, "Press space to start chess game", getWidth() / 2,
                getHeight() / 2.8);

        drawTextureOption(g);
        drawModeOption(g);
        if (model.isAiOpposition()) {
            drawPlayAsColorOption(g);
            drawAiLevelOption(g);
        }

    }

    private void drawTextureOption(Graphics2D g) {
        g.setFont(fontTheme.getOptionFont());
        int height = getHeight() / 2;
        String option = "Texture:";
        Color textColor = (model.getSelectedOption() == Option.TEXTURE ? colorTheme.getSelectedMenuOptionsColor()
                : colorTheme.getMenuOptionsColor());
        g.setColor(textColor);
        Inf101Graphics.drawCenteredString(g, option, getWidth() / 5, height);

        String option1 = "Default chess";
        String option2 = "Star wars";

        g.setFont((textureTheme instanceof DefaultTextureTheme ? fontTheme.getSelectedOptionFont()
                : fontTheme.getNonSelectedOptionFont()));
        Inf101Graphics.drawCenteredString(g, option1, getWidth() / 2, height);
        g.setFont((textureTheme instanceof StarWarsTextureTheme ? fontTheme.getSelectedOptionFont()
                : fontTheme.getNonSelectedOptionFont()));
        Inf101Graphics.drawCenteredString(g, option2, getWidth() / 2 + getWidth() / 3, height);
    }

    private void drawModeOption(Graphics2D g) {
        g.setFont(fontTheme.getOptionFont());
        int height = getHeight() / 2 + (getHeight() / 12);
        String option = "Mode:";
        Color textColor = (model.getSelectedOption() == Option.MULTIPLAYER ? colorTheme.getSelectedMenuOptionsColor()
                : colorTheme.getMenuOptionsColor());
        g.setColor(textColor);
        Inf101Graphics.drawCenteredString(g, option, getWidth() / 5, height);

        String option1 = "Two-player";
        String option2 = "Play against computer";

        g.setFont((model.isAiOpposition() ? fontTheme.getNonSelectedOptionFont() : fontTheme.getSelectedOptionFont()));
        Inf101Graphics.drawCenteredString(g, option1, getWidth() / 2, height);
        g.setFont((!model.isAiOpposition() ? fontTheme.getNonSelectedOptionFont() : fontTheme.getSelectedOptionFont()));
        Inf101Graphics.drawCenteredString(g, option2, getWidth() / 2 + getWidth() / 3.5, height);
    }

    private void drawPlayAsColorOption(Graphics2D g) {
        g.setFont(fontTheme.getOptionFont());
        int height = getHeight() / 2 + (getHeight() / 12 * 2);
        String option = "Play as:";
        Color textColor = (model.getSelectedOption() == Option.COLOR ? colorTheme.getSelectedMenuOptionsColor()
                : colorTheme.getMenuOptionsColor());
        g.setColor(textColor);
        Inf101Graphics.drawCenteredString(g, option, getWidth() / 5, height);

        String option1 = "White";
        String option2 = "Black";

        g.setFont((model.getPlayerColor() == ChessColor.WHITE ? fontTheme.getSelectedOptionFont()
                : fontTheme.getNonSelectedOptionFont()));
        Inf101Graphics.drawCenteredString(g, option1, getWidth() / 2, height);
        g.setFont((model.getPlayerColor() == ChessColor.BLACK ? fontTheme.getSelectedOptionFont()
                : fontTheme.getNonSelectedOptionFont()));
        Inf101Graphics.drawCenteredString(g, option2, getWidth() / 2 + getWidth() / 3, height);
    }

    private void drawAiLevelOption(Graphics2D g) {
        g.setFont(fontTheme.getOptionFont());
        int height = getHeight() / 2 + (getHeight() / 12 * 3);
        String option = "Difficulty:";
        Color textColor = (model.getSelectedOption() == Option.DIFFICULTY ? colorTheme.getSelectedMenuOptionsColor()
                : colorTheme.getMenuOptionsColor());
        g.setColor(textColor);
        Inf101Graphics.drawCenteredString(g, option, getWidth() / 5, height);

        String option1 = "Easy";
        String option2 = "Medium";
        String option3 = "Hard";

        g.setFont((model.getAiPlayer() instanceof RandomAI ? fontTheme.getSelectedOptionFont()
                : fontTheme.getNonSelectedOptionFont()));
        Inf101Graphics.drawCenteredString(g, option1, getWidth() / 2, height);
        g.setFont((model.getAiPlayer() instanceof AggressiveAI ? fontTheme.getSelectedOptionFont()
                : fontTheme.getNonSelectedOptionFont()));
        Inf101Graphics.drawCenteredString(g, option2, getWidth() / 2 + getWidth() / 5, height);
        g.setFont((model.getAiPlayer() instanceof CarefulAI ? fontTheme.getSelectedOptionFont()
                : fontTheme.getNonSelectedOptionFont()));
        Inf101Graphics.drawCenteredString(g, option3, getWidth() / 2 + (getWidth() / 5) * 2, height);
    }

    private void drawGame(Graphics2D g) {
        double width = this.getWidth() - 2 * HORIZONTAL_OUTERMARGIN;
        double height = this.getHeight() - 2 * VERTICAL_OUTERMARGIN;

        Rectangle2D baseBox = new Rectangle2D.Double(HORIZONTAL_OUTERMARGIN, VERTICAL_OUTERMARGIN, width, height);
        SquareToPixelConverter squareConverter = new SquareToPixelConverter(baseBox, model.getBoard(), CELL_MARGIN);

        drawBoard(g, squareConverter, model.getBoard(), colorTheme, textureTheme);
        drawCoordinates(g);
        if (model.getLastMoveFrom() != null)
            drawLastMove(g, squareConverter);
        if (model.getSelectedSquare() != null) {
            drawSelectedSquare(g, squareConverter);
            drawLegalMoves(g, squareConverter);
        }

        if (model.getGameState() == GameState.CHECKMATE || model.getGameState() == GameState.STALEMATE)
            drawGameOver(g, baseBox);

    }

    private static void drawBoard(Graphics2D g, SquareToPixelConverter cp, ChessBoard board,
            ColorTheme colorTheme, TextureTheme textureTheme) {
        for (Square square : board) {
            Rectangle2D rectangle = cp.getBoundsForCell(square);
            g.setColor(colorTheme.getSquareColor(square));
            g.fill(rectangle);
            Piece piece = square.getPiece();
            if (piece != null) {
                BufferedImage img = Inf101Graphics.loadImageFromResources(textureTheme.getImgPath(piece));
                Inf101Graphics.drawCenteredImage(g, img, rectangle.getCenterX(), rectangle.getCenterY(), 0.45);
            }
        }
    }

    private void drawCoordinates(Graphics2D g) {
        for (int i = 1; i < 9; i++) {
            double y = getHeight()
                    - (VERTICAL_OUTERMARGIN + CELL_MARGIN + (i - 1) * (squareHeight) + squareHeight / 2);
            g.setColor(colorTheme.getCoordinatesColor());
            g.setFont(fontTheme.getDefaultFont());
            Inf101Graphics.drawCenteredString(g, String.valueOf(i), HORIZONTAL_OUTERMARGIN / 2, y);
        }

        for (Column column : Column.values()) {
            double x = HORIZONTAL_OUTERMARGIN + CELL_MARGIN + column.ordinal() * (squareWidth)
                    + squareWidth / 2;
            g.setColor(colorTheme.getCoordinatesColor());
            g.setFont(fontTheme.getDefaultFont());
            Inf101Graphics.drawCenteredString(g, column.toString(), x, getHeight() - VERTICAL_OUTERMARGIN / 2);
        }
    }

    private void drawSelectedSquare(Graphics2D g, SquareToPixelConverter squareConverter) {
        Rectangle2D selectedSquare = squareConverter.getBoundsForCell(model.getSelectedSquare());
        g.setColor(colorTheme.getSelectedSquareColor());
        g.setStroke(new BasicStroke(3));
        g.draw(selectedSquare);
    }

    private void drawLegalMoves(Graphics2D g, SquareToPixelConverter squareConverter) {
        Piece selectedPiece = model.getSelectedSquare().getPiece();
        ArrayList<Square> legalMoves = selectedPiece.getLegalMoves();

        for (Square square : legalMoves) {
            Rectangle2D squarePos = squareConverter.getBoundsForCell(square);
            int diameter = 15;
            int radius = diameter / 2;
            Ellipse2D circle = new Ellipse2D.Double(squarePos.getCenterX() - radius, squarePos.getCenterY() - radius,
                    diameter,
                    diameter);
            g.setColor(colorTheme.getSelectedSquareColor());
            g.fill(circle);
        }
    }

    private void drawLastMove(Graphics2D g, SquareToPixelConverter squareConverter) {
        Rectangle2D lastMoveFrom = squareConverter.getBoundsForCell(model.getLastMoveFrom());
        Rectangle2D lastMoveTo = squareConverter.getBoundsForCell(model.getLastMoveTo());
        g.setColor(colorTheme.getLastMoveSquareColor());
        g.setStroke(new BasicStroke(3));
        g.draw(lastMoveFrom);
        g.draw(lastMoveTo);
    }

    private void drawGameOver(Graphics2D g, Rectangle2D baseBox) {
        g.setColor(colorTheme.getGameOverScreenColor());
        g.fill(baseBox);

        if (model.getGameState() == GameState.CHECKMATE) {
            String s = String.format("%s is CHECKMATE!", model.getWinner().toggle());
            String s2 = String.format("%s won the game.", model.getWinner());
            g.setColor(colorTheme.getGameOverTextColor());
            g.setFont(fontTheme.getGameOverScreenFont());
            Inf101Graphics.drawCenteredString(g, s, getBounds().width / 2, getBounds().height / 2);
            g.setFont(fontTheme.getGameOverScreenFont());
            Inf101Graphics.drawCenteredString(g, s2, getBounds().width / 2,
                    getBounds().height / 2 + fontTheme.getGameOverScreenFont().getSize());
        } else {
            String s = "STALEMATE!";
            String s2 = "The game ended in a draw.";
            g.setColor(colorTheme.getGameOverTextColor());
            g.setFont(fontTheme.getGameOverScreenFont());
            Inf101Graphics.drawCenteredString(g, s, getBounds().width / 2, getBounds().height / 2);
            g.setFont(fontTheme.getGameOverScreenFont());
            Inf101Graphics.drawCenteredString(g, s2, getBounds().width / 2,
                    getBounds().height / 2 + fontTheme.getGameOverScreenFont().getSize());
        }
    }
}
