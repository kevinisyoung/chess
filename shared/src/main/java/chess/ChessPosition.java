package chess;

/**
 * Represents a single square position on a chess board
 * <p>
 * Note: You can add to this class, but you may not alter
 * signature of the existing methods.
 */
public class ChessPosition {
    private int row = 0;
    private int column = 0;

    public ChessPosition(int row, int col) {
        this.row = row;
        this.column = col;
    }

    /**
     * @return which row this position is in
     * 1 codes for the bottom row
     */
    public int getRow() {
        return row;
    }

    /**
     * @return which column this position is in
     * 1 codes for the left row
     */
    public int getColumn() {
        return column;
    }

    public Boolean isValid() {
        //its invalid
        return column <= 8 && column >= 1 && row <= 8 && row >= 1;
    }
}
