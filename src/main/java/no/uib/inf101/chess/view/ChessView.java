package no.uib.inf101.chess.view;

import javax.swing.JPanel;

import no.uib.inf101.chess.model.ChessBoard;
import no.uib.inf101.chess.model.ChessModel;
import no.uib.inf101.chess.model.Column;
import no.uib.inf101.chess.model.Square;
import no.uib.inf101.chess.model.pieces.IPiece;
import no.uib.inf101.chess.view.design.ColorTheme;
import no.uib.inf101.chess.view.design.DefaultColorTheme;
import no.uib.inf101.chess.view.design.DefaultTextureTheme;
import no.uib.inf101.chess.view.design.TextureTheme;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;

public class ChessView extends JPanel {

    private ViewableModel model;
    private ColorTheme colorTheme;
    private TextureTheme textureTheme;

    double boardWidth;
    double boardHeight;
    double squareWidth;
    double squareHeight;

    private static final double VERTICAL_OUTERMARGIN = 50;
    private static final double HORIZONTAL_OUTERMARGIN = 50;
    private static final double CELL_SIZE = 80;
    private static final double CELL_MARGIN = 1;
    private static final int STANDARD_FONT_SIZE = 16;
    private static final Font STANDARD_FONT = new Font("Calibri", Font.BOLD, STANDARD_FONT_SIZE);

    public ChessView(ChessModel model) {
        double windowWidth = (CELL_SIZE + CELL_MARGIN) * model.getDimension().cols() + CELL_MARGIN
                + HORIZONTAL_OUTERMARGIN;
        double windowHeight = (CELL_SIZE + CELL_MARGIN) * model.getDimension().rows() + CELL_MARGIN
                + VERTICAL_OUTERMARGIN;
        this.boardWidth = windowWidth - 2 * HORIZONTAL_OUTERMARGIN;
        this.boardHeight = windowHeight - 2 * VERTICAL_OUTERMARGIN;
        this.squareWidth = boardWidth / 8;
        this.squareHeight = boardHeight / 8;

        this.setFocusable(true);
        this.setPreferredSize(new Dimension((int) windowWidth, (int) windowHeight));
        this.model = model;
        this.colorTheme = new DefaultColorTheme();
        this.textureTheme = new DefaultTextureTheme();
        this.setBackground(this.colorTheme.getBackgroundColor());
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;

        drawGame(g2);
    }

    public void drawGame(Graphics2D g) {
        double width = this.getWidth() - 2 * HORIZONTAL_OUTERMARGIN;
        double height = this.getHeight() - 2 * VERTICAL_OUTERMARGIN;

        Rectangle2D baseBox = new Rectangle2D.Double(HORIZONTAL_OUTERMARGIN, VERTICAL_OUTERMARGIN, width, height);
        SquareToPixelConverter squareConverter = new SquareToPixelConverter(baseBox, model.getDimension(), CELL_MARGIN);

        drawBoard(g, squareConverter, model.getBoard(), colorTheme, textureTheme);
        drawCoordinates(g);
        drawSelectedSquare(g, squareConverter);
    }

    private static void drawBoard(Graphics2D g, SquareToPixelConverter cp, ChessBoard board,
            ColorTheme colorTheme, TextureTheme textureTheme) {
        for (Square square : board) {
            Rectangle2D rectangle = cp.getBoundsForCell(square);
            g.setColor(colorTheme.getSquareColor(square));
            g.fill(rectangle);
            IPiece piece = square.getPiece();
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
            g.setFont(STANDARD_FONT);
            Inf101Graphics.drawCenteredString(g, String.valueOf(i), HORIZONTAL_OUTERMARGIN / 2, y);
            // Inf101Graphics.drawCenteredString(g, String.valueOf(i), getWidth() -
            // HORIZONTAL_OUTERMARGIN / 2, y);

        }

        for (Column column : Column.values()) {
            double x = HORIZONTAL_OUTERMARGIN + CELL_MARGIN + column.ordinal() * (squareWidth)
                    + squareWidth / 2;
            g.setColor(colorTheme.getCoordinatesColor());
            g.setFont(STANDARD_FONT);
            // Inf101Graphics.drawCenteredString(g, column.toString(), x,
            // VERTICAL_OUTERMARGIN / 2);
            Inf101Graphics.drawCenteredString(g, column.toString(), x, getHeight() - VERTICAL_OUTERMARGIN / 2);
        }
    }

    private void drawSelectedSquare(Graphics2D g, SquareToPixelConverter squareConverter) {
        Rectangle2D selectedSquare = squareConverter.getBoundsForCell(model.getSelectedSquare());
        g.setColor(colorTheme.getSelectedSquareColor());
        g.draw(selectedSquare);
    }
}
