package no.uib.inf101.chess.view;

import javax.swing.JPanel;

import no.uib.inf101.chess.model.ChessBoard;
import no.uib.inf101.chess.model.ChessModel;
import no.uib.inf101.chess.model.Square;
import no.uib.inf101.chess.view.design.ColorTheme;
import no.uib.inf101.chess.view.design.DefaultColorTheme;
import no.uib.inf101.chess.view.design.DefaultTextureTheme;
import no.uib.inf101.chess.view.design.TextureTheme;
import no.uib.inf101.grid.GridCell;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;
import java.awt.geom.Ellipse2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;

public class ChessView extends JPanel {

    private ViewableModel model;
    private ColorTheme colorTheme;
    private TextureTheme textureTheme;

    private static final double VERTICAL_OUTERMARGIN = 50;
    private static final double HORIZONTAL_OUTERMARGIN = 50;
    private static final double CELL_SIZE = 80;
    private static final double CELL_MARGIN = 1;
    private static final int STANDARD_FONT_SIZE = 40;

    public ChessView(ChessModel model) {
        double windowWidth = (CELL_SIZE + CELL_MARGIN) * model.getDimension().cols() + CELL_MARGIN
                + HORIZONTAL_OUTERMARGIN;
        double windowHeight = (CELL_SIZE + CELL_MARGIN) * model.getDimension().rows() + CELL_MARGIN
                + VERTICAL_OUTERMARGIN;
        double boardWidth = windowWidth - 2 * HORIZONTAL_OUTERMARGIN;
        double boardHeight = windowHeight - 2 * VERTICAL_OUTERMARGIN;
        double squareWidth = boardWidth / 8;
        double squareHeight = boardHeight / 8;

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

        Rectangle2D window = new Rectangle2D.Double(HORIZONTAL_OUTERMARGIN, VERTICAL_OUTERMARGIN, width, height);
        CellPositionToPixelConverter cp = new CellPositionToPixelConverter(window, model.getDimension(), CELL_MARGIN);

        drawCells(g, cp, model.getBoard(), colorTheme);
        drawPieces(g, cp, model.getBoard(), textureTheme);
    }

    private static void drawCells(Graphics2D g, CellPositionToPixelConverter cp, ChessBoard board,
            ColorTheme colorTheme) {
        for (Square square : board) {
            Rectangle2D rectangle = cp.getBoundsForCell(square);
            g.setColor(colorTheme.getSquareColor(square));
            g.fill(rectangle);
        }
    }

    private static void drawPieces(Graphics2D g, CellPositionToPixelConverter cp, ChessBoard board,
            TextureTheme textureTheme) {
        for (Square square : board) {
            if (square.getPiece() != null) {

            }
        }
    }
}
