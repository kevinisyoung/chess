package chess;

/**
 * A chessboard that can hold and rearrange chess pieces.
 * <p>
 * Note: You can add to this class, but you may not alter
 * signature of the existing methods.
 */
public class ChessBoard {
    private ChessPiece[][] chessBoard = new ChessPiece[8][8];

    public ChessBoard() {
        System.out.println(toString());
    }

    /**
     * Adds a chess piece to the chessboard
     *
     * @param position where to add the piece to
     * @param piece    the piece to add
     */
    public void addPiece(ChessPosition position, ChessPiece piece) {
        chessBoard[position.getRow()][position.getColumn()] = piece;
    }

    /**
     * Gets a chess piece on the chessboard
     *
     * @param position The position to get the piece from
     * @return Either the piece at the position, or null if no piece is at that
     * position
     */
    public ChessPiece getPiece(ChessPosition position) {
        return chessBoard[position.getRow()][position.getColumn()];
    }

    /**
     * Sets the board to the default starting board
     * (How the game of chess normally starts)
     */
    public void resetBoard() {
        throw new RuntimeException("Not implemented");
    }

    @Override
    public String toString() {
        StringBuilder output = new StringBuilder();
        for (int rows = 0; rows < 8; rows++) {
            output.append("|");
            for (int cols = 0; cols < 8; cols++) {
                char pieceChar = ' ';
                if (chessBoard[rows][cols] == null) {
                    //do nothing
                }
                else if (chessBoard[rows][cols].getPieceType() == ChessPiece.PieceType.BISHOP){
                    pieceChar = 'B';
                }else if (chessBoard[rows][cols].getPieceType() == ChessPiece.PieceType.KING){
                    pieceChar = 'K';
                }else if (chessBoard[rows][cols].getPieceType() == ChessPiece.PieceType.QUEEN){
                    pieceChar = 'Q';
                }else if (chessBoard[rows][cols].getPieceType() == ChessPiece.PieceType.KNIGHT){
                    pieceChar = 'N';
                }else if (chessBoard[rows][cols].getPieceType() == ChessPiece.PieceType.ROOK){
                    pieceChar = 'R';
                }else if (chessBoard[rows][cols].getPieceType() == ChessPiece.PieceType.PAWN){
                    pieceChar = 'B';
                }
                if (chessBoard[rows][cols] != null && chessBoard[rows][cols].getTeamColor() == ChessGame.TeamColor.BLACK){
                    pieceChar = String.valueOf(pieceChar).toLowerCase().charAt(0);
                }
                output.append(pieceChar);
                output.append("|");
            }
            output.append("\n");
        }
        return output.toString();
    }
}
