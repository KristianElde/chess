package no.uib.inf101.grid;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * Represents a two-dimensional grid data structure with elements of type E.
 * This class implements the IGrid interface, providing functionalities for
 * creating, accessing, and modifying elements in the grid.
 * 
 * @param <E> The type of elements stored in the grid.
 */
public class Grid<E> implements IGrid<E> {

  /**
   * The number of rows in the grid.
   */
  private int rows;

  /**
   * The number of columns in the grid.
   */
  private int cols;

  /**
   * The underlying data structure to hold grid elements. It is implemented as a
   * list of list, where each inner list represents a row in the grid.
   */
  private ArrayList<ArrayList<E>> grid;

  /**
   * Constructs a new Grid with the specified number of rows and columns. All grid
   * cells are initialized with null values.
   * 
   * @param rows The number of rows in the grid.
   * @param cols The number of columns in the grid.
   */
  public Grid(int rows, int cols) {
    this(rows, cols, null);
  }

  /**
   * Constructs a new Grid with the specified number of rows and columns,
   * initializing all cells with a default value.
   * 
   * @param rows         The number of rows in the grid.
   * @param cols         The number of columns in the grid.
   * @param defaultValue The default value to initialize all cells in the grid.
   */
  public Grid(int rows, int cols, E defaultValue) {
    this.cols = cols;
    this.rows = rows;

    this.grid = new ArrayList<ArrayList<E>>();

    for (int i = 0; i < this.rows; i++) {
      ArrayList<E> row = new ArrayList<>();
      for (int j = 0; j < this.cols; j++) {
        row.add(defaultValue);
      }
      this.grid.add(row);
    }
  }

  /**
   * Returns the grid as a two-dimensional ArrayList representation.
   * 
   * @return The data-structure which holds the value of all the cells in a grid.
   */
  public ArrayList<ArrayList<E>> getGrid() {
    return this.grid;
  }

  @Override
  public int rows() {
    return this.rows;
  }

  @Override
  public int cols() {
    return this.cols;
  }

  @Override
  public Iterator<GridCell<E>> iterator() {
    ArrayList<GridCell<E>> grid = new ArrayList<>();

    for (int i = 0; i < this.rows; i++) {
      for (int j = 0; j < this.cols; j++) {
        CellPosition cellPos = new CellPosition(i, j);
        E value = this.get(cellPos);
        GridCell<E> cell = new GridCell<>(cellPos, value);
        grid.add(cell);
      }
    }

    return grid.iterator();
  }

  @Override
  public E get(CellPosition pos) {
    if (!positionIsOnGrid(pos)) {
      throw new IndexOutOfBoundsException();
    }

    ArrayList<E> row = this.grid.get(pos.row());
    return row.get(pos.col());
  }

  @Override
  public void set(CellPosition pos, E value) {
    if (!positionIsOnGrid(pos)) {
      throw new IndexOutOfBoundsException();
    }

    ArrayList<E> row = this.grid.get(pos.row());
    row.set(pos.col(), value);
  }

  @Override
  public boolean positionIsOnGrid(CellPosition pos) {
    if (pos.row() < 0 || pos.row() >= this.rows || pos.col() < 0 || pos.col() >= this.cols) {
      return false;
    }
    return true;
  }
}
