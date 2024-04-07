package no.uib.inf101.grid;

/**
 * Represents the dimension of a grid, providing methods to retrieve the number
 * of rows and columns. This interface defines basic functionalities for
 * obtaining the dimensions of a grid.
 */
public interface GridDimension {

  /** Number of rows in the grid */
  int rows();

  /** Number of columns in the grid */
  int cols();
}
