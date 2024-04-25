package no.uib.inf101.chess.view;

import java.awt.geom.Rectangle2D;

import no.uib.inf101.chess.model.Square;
import no.uib.inf101.grid.GridDimension;

/**
 * The SquareToPixelConverter class converts squares in a
 * chess board to corresponding pixel bounds within a specified rectangle.
 */
public class SquareToPixelConverter {

  /**
   * The rectangle defining the bounds for the converted pixel positions.
   */
  private Rectangle2D box;

  /** The grid dimension used for conversion. */
  private GridDimension gd;

  /** The margin between cells. */
  private double margin;

  /**
   * Constructs a SquareToPixelConverter with the given parameters.
   * 
   * @param box    The rectangle defining the bounds for the converted
   *               pixel positions.
   * @param gd     The grid dimension used for conversion.
   * @param margin The margin between cells.
   */
  public SquareToPixelConverter(Rectangle2D box, GridDimension gd, double margin) {
    this.box = box;
    this.gd = gd;
    this.margin = margin;
  }

  /**
   * Gets the bounds for the specified cell position in pixels.
   * 
   * @param pos The square to convert to pixel bounds.
   * @return The rectangle representing the pixel bounds for the specified
   *         cell position.
   */
  Rectangle2D getBoundsForCell(Square pos) {
    double cellWidth = (this.box.getWidth() - this.margin * (this.gd.cols() + 1)) / this.gd.cols();
    double cellHeight = (this.box.getHeight() - this.margin * (this.gd.rows() + 1)) / this.gd.rows();
    double x = box.getX() + this.margin + pos.col().ordinal() * (cellWidth + this.margin);
    double y = box.getY() + box.getHeight() - (this.margin + (pos.row() - 1)) * (cellHeight + this.margin);

    Rectangle2D rectangle = new Rectangle2D.Double(x, y, cellWidth, cellHeight);

    return rectangle;
  }
}
