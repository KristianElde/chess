package no.uib.inf101.grid;

/**
 * Represents a cell in a grid data structure. Each cell is associated with a
 * position and a value.
 * 
 * @param <E> The type of value stored in the cell.
 */
public record GridCell<E>(CellPosition pos, E value) {
}
